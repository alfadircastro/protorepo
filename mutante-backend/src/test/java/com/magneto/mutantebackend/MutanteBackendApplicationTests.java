package com.magneto.mutantebackend;

import com.magneto.mutantebackend.controller.MutanteController;
import com.magneto.mutantebackend.dto.EstadisticaDto;
import com.magneto.mutantebackend.dto.SecuenciaADNDto;
import com.magneto.mutantebackend.services.MutanteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class MutanteBackendApplicationTests {

	@Autowired
	MutanteController mutanteController;

	@Test
	void contextLoads() {

		/*
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
		*/

		String[] arrayDNA1 = {
			"ATGCGATTBG",
			"CTGTACTCAA",
			"TTATGGAAGG",
			"AGAATGTTGG",
			"CTCCTACCTA",
			"TCACTAGCTT",
			"CACGTAACTT",
			"GCGAACACCT",
			"AACCTGACAA",
			"CCGTCCTTTA",
			"GCTAACAGCT",
			"GGTAACAGCT"
		};
		
		SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
		secuenciaADNDto.setDna(arrayDNA1);

		ResponseEntity<?> responseEntity = mutanteController.detectarMutante(secuenciaADNDto);

		System.out.print("detectarMutante: " + responseEntity.toString());

		responseEntity = mutanteController.consultarEstadisticas();

		System.out.print("consultarEstadisticas: " + responseEntity.getBody().toString());

		/*
		boolean resp = mutanteService.isMutant(secuenciaADNDto);

		if (resp) {
			System.out.println("Si es mutante");
		}
		else {
			System.out.println("No es mutante");
		}

		EstadisticaDto estadisticaDto = mutanteService.consultarEstadisticas2();

		System.out.println("Ratio: " + estadisticaDto.getRatio());
		*/
	}

}
