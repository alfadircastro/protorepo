package com.magneto.mutantebackend;

import com.magneto.mutantebackend.dto.EstadisticaDto;
import com.magneto.mutantebackend.dto.SecuenciaADNDto;
import com.magneto.mutantebackend.services.MutanteService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MutanteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutanteBackendApplication.class, args);
	}

	//-- Test
	/*
	@Bean
	CommandLineRunner init(MutanteService mutanteService) {
		return args -> {

			String[] arrayDNA = {
				"ATGCGATTBG",
				"CAGTGCTCAG",
				"TTATGTAAGG",
				"AGAAGGTTGG",
				"CCCCTACCCC",
				"TCACTAGCTT",
				"CACGTAACTT",
				"GCCAACAACT",
				"AACCTGACAT",
				"CCGTCCTTTT"
			};

			String[] arrayDNA1 = {
				"ATGCGATTBG",
				"CTGTACTCAA",
				"TTATGGAAGG",
				"AGAATGTTGG",
				"CTCCTACCTA",
				"TCACTAGCTT",
				"CACGTAACTT",
				"GCGAACAGCT",
				"AACCTGACAA",
				"CCGTCCTTTA",
				"GCTAACAGCT",
				"GGTAACAGCT"
			};
			
			SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
			secuenciaADNDto.setDna(arrayDNA1);

			boolean resp = mutanteService.isMutant(secuenciaADNDto);

			if (resp) {
				System.out.println("Si es mutante");
			}
			else {
				System.out.println("No es mutante");
			}

			EstadisticaDto estadisticaDto = mutanteService.calcularEstadisticas();

			System.out.println("Ratio: " + estadisticaDto.getRatio());

		};
	}
	*/

}
