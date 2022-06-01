package com.magneto.mutantebackend.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.magneto.mutantebackend.dto.EstadisticaDto;
import com.magneto.mutantebackend.dto.SecuenciaADNDto;
import com.magneto.mutantebackend.model.Estadistica;
import com.magneto.mutantebackend.model.SecuenciaADN;
import com.magneto.mutantebackend.repository.EstadisticaRepository;
import com.magneto.mutantebackend.repository.SecuenciaADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutanteService implements Serializable {

    @Autowired
    private SecuenciaADNRepository secuenciaADNRepository;

    @Autowired
    private EstadisticaRepository estadisticaRepository;

    // Constantes del proceso
    final int TAM_SECUEN_MUTANTE = 4;
    final int CANT_SECUEN_MUTANTE = 2;
    final Long STAT_ID_MUTANT = 1L;
    
    private int acumSecuenMutante;
    private static boolean recalcularEstadisticas = true;

    public boolean isMutant(SecuenciaADNDto secuenciaADNDto) {

        // El array bidimensional puede ser N x N o M x N
        String[] arrayADN = secuenciaADNDto.getDna();
        boolean resp = false;

        this.acumSecuenMutante = 0;

        // Busqueda horizontal
        buscarSecuenciaH(arrayADN);

        // En todos los casos de busqueda si se cumple la condicion para ser mutante la busqueda no continua
        if (this.acumSecuenMutante >= CANT_SECUEN_MUTANTE) {
            resp = true;
        }
        else {
            // Busqueda vertical            
            buscarSecuenciaV(arrayADN);

            if (this.acumSecuenMutante >= CANT_SECUEN_MUTANTE) {
                resp = true;
            }
            else {
                // Busqueda en direccion diagonal principal
                buscarSecuenciaDP(arrayADN);

                if (this.acumSecuenMutante >= CANT_SECUEN_MUTANTE) {
                    resp = true;
                }
                else {
                    // Busqueda en direccion diagonal secundaria
                    this.buscarSecuenciaDS(arrayADN);

                    if (acumSecuenMutante >= CANT_SECUEN_MUTANTE) {
                        resp = true;
                    }
                }
            }
        }

        // La secuencia ADN recibida y el resultado del analisis se guardan en la bd
        SecuenciaADN secuenciaADN = new SecuenciaADN(arrayADN, resp);
        secuenciaADNRepository.save(secuenciaADN);

        // Establece que se deben recalcular las estadisticas cuando se consulten
        MutanteService.recalcularEstadisticas = true;

        return resp;
    }

    // Recorre el array bidimensional de forma horizontal
    private void buscarSecuenciaH(String[] arrayADN) {

        for (String cadena : arrayADN) {
            this.acumSecuenMutante += contarSecuenciaMutante(cadena);
            // Si se cumple la condicion para ser mutante, la busqueda no continua
            if (this.acumSecuenMutante >= CANT_SECUEN_MUTANTE) {
                break;
            }
        }
    }

    // Recorre el array bidimensional de forma vertical
    private void buscarSecuenciaV(String[] arrayADN) {
        int nCols = arrayADN[0].length();

        for (int i = 0; i < nCols && this.acumSecuenMutante < CANT_SECUEN_MUTANTE; i++) {
            String cadenaT = new String();
            for (String cadena : arrayADN) {
                cadenaT = cadenaT + cadena.charAt(i); 
            }

            this.acumSecuenMutante += contarSecuenciaMutante(cadenaT);
        }
    }

    // Recorre el array bidimensional en direccion de la diagonal principal
    private void buscarSecuenciaDP(String[] arrayADN) {
        int nCols = arrayADN[0].length();

        // Extrae cadenas que se encuentren en la parte superior de la diagonal principal
        for (int col = 0; col <= (nCols - TAM_SECUEN_MUTANTE) && this.acumSecuenMutante < CANT_SECUEN_MUTANTE; col++) {
            String cadenaD = new String();
            
            int i = 0;
            for (int j = col; j < nCols; j++) {
                cadenaD = cadenaD + arrayADN[i].charAt(j);
                i ++;
            }

            this.acumSecuenMutante += contarSecuenciaMutante(cadenaD);
        }

        if (this.acumSecuenMutante < CANT_SECUEN_MUTANTE) {
            int nFilas = arrayADN.length;

            // Extrae cadenas que se encuentren en la parte inferior de la diagonal principal
            for (int fila = 1; fila <= (nFilas - TAM_SECUEN_MUTANTE) && this.acumSecuenMutante < CANT_SECUEN_MUTANTE; fila++) {
                String cadenaD = new String();
                
                int j = 0;
                for (int i = fila; i < nFilas && j < nCols; i++) {
                    cadenaD = cadenaD + arrayADN[i].charAt(j);
                    j ++;
                }
    
                this.acumSecuenMutante += contarSecuenciaMutante(cadenaD);    
            }
        }

    }

    // Recorre el array bidimensional en direccion de la diagonal secundaria 
    private void buscarSecuenciaDS(String[] arrayADN) {
        int nCols = arrayADN[0].length();

        // Extrae cadenas que se encuentren en la parte superior de la diagonal secundaria
        for (int col = TAM_SECUEN_MUTANTE - 1; col < nCols && this.acumSecuenMutante < CANT_SECUEN_MUTANTE; col++) {
            String cadenaD = new String();
            
            int i = 0;
            for (int j = col; j >= 0; j--) {
                cadenaD = cadenaD + arrayADN[i].charAt(j);
                i ++;
            }

            this.acumSecuenMutante += contarSecuenciaMutante(cadenaD);
        }

        if (this.acumSecuenMutante < CANT_SECUEN_MUTANTE) {
            int nFilas = arrayADN.length;

            // Extrae cadenas que se encuentren en la parte inferior de la diagonal secundaria
            for (int fila = 1; fila <= (nFilas - TAM_SECUEN_MUTANTE) && this.acumSecuenMutante < CANT_SECUEN_MUTANTE; fila++) {
                String cadenaD = new String();
                
                int j = nCols - 1;
                for (int i = fila; i < nFilas && j >= 0; i++) {
                    cadenaD = cadenaD + arrayADN[i].charAt(j);
                    j --;
                }
    
                this.acumSecuenMutante += contarSecuenciaMutante(cadenaD);    
            }
        }

    }

    // Cuenta secuencias repetidas de longitud TAM_SECUEN_MUTANTE
    private int contarSecuenciaMutante(String strADN) {
        char[] arrayADN = strADN.toCharArray();
        int contCaracteres = 1;
        int contCadenas = 0;

        char caracter = '0';

        for (char c : arrayADN) {
            if (c == caracter) {
                contCaracteres ++;
            }
            else {
                caracter = c;
                contCaracteres = 1;
            }

            if (contCaracteres == TAM_SECUEN_MUTANTE) {
                contCadenas ++;
            }

            if (contCadenas >= CANT_SECUEN_MUTANTE) {
                break;
            } 
        }

        if (contCadenas > 0) {
            System.out.println(strADN);
        }

        return contCadenas;

    }

    // Consulta las estadisticas en la bd y las recalcula si es necesario
    /*
    public EstadisticaDto consultarEstadisticas() {

        Optional<Estadistica> optEstadistica = estadisticaRepository.findEstadisticaByStat_id(STAT_ID_MUTANT);

        EstadisticaDto estadisticaDto = null;

        if (optEstadistica.isPresent()) {
            Estadistica estadistica = optEstadistica.get();
            // Con base en recalcularEstadisticas se determina si es necesario recalcular las estadisticas
            if (!MutanteService.recalcularEstadisticas) {
                estadisticaDto = new EstadisticaDto();
                convertToEstadisticaDto(estadistica, estadisticaDto);
            }
            else {
                estadisticaDto = calcularEstadisticas();
                MutanteService.recalcularEstadisticas = false;
                
                convertToEstadistica(estadisticaDto, estadistica);
                estadisticaRepository.save(estadistica);
            }
        }
        else {
            estadisticaDto = calcularEstadisticas();
            MutanteService.recalcularEstadisticas = false;

            Estadistica estadistica = new Estadistica();
            estadistica.setStat_id(STAT_ID_MUTANT);
            convertToEstadistica(estadisticaDto, estadistica);

            estadisticaRepository.save(estadistica);
        }

        return estadisticaDto;

    }

    private void convertToEstadisticaDto(Estadistica estadistica, EstadisticaDto estadisticaDto) {
        estadisticaDto.setCount_mutant_dna(estadistica.getCount_mutant_dna());
        estadisticaDto.setCount_human_dna(estadistica.getCount_human_dna());
        estadisticaDto.setRatio(estadistica.getRatio());
    }

    private void convertToEstadistica(EstadisticaDto estadisticaDto, Estadistica estadistica) {
        estadistica.setCount_mutant_dna(estadisticaDto.getCount_mutant_dna());
        estadistica.setCount_human_dna(estadisticaDto.getCount_human_dna());
        estadistica.setRatio(estadisticaDto.getRatio());
    }
    */

    // Calcula las estadisticas
    public EstadisticaDto consultarEstadisticas2() {

        EstadisticaDto estadisticaDto = calcularEstadisticas();

        return estadisticaDto;

    }

    private EstadisticaDto calcularEstadisticas() {
        List<SecuenciaADN> secuenciaADNList = new ArrayList<SecuenciaADN>();
        secuenciaADNList = secuenciaADNRepository.findSecuenciaADNByMutant_dna(true);

        EstadisticaDto estadisticaDto = null;

        if (! secuenciaADNList.isEmpty()) {
            Long count_mutant_dna = Long.valueOf(secuenciaADNList.size());
            Long count_human_dna = secuenciaADNRepository.count() - count_mutant_dna;

            estadisticaDto = new EstadisticaDto();
            estadisticaDto.setCount_mutant_dna(count_mutant_dna);
            estadisticaDto.setCount_human_dna(count_human_dna);
            Float ratio = count_mutant_dna.floatValue() / count_human_dna.floatValue();
            estadisticaDto.setRatio(ratio);
        }

        return estadisticaDto;

    }
}