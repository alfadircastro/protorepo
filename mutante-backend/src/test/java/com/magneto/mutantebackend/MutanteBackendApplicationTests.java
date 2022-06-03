package com.magneto.mutantebackend;

import com.magneto.mutantebackend.controller.MutanteController;
import com.magneto.mutantebackend.dto.EstadisticaDto;
import com.magneto.mutantebackend.dto.SecuenciaADNDto;
import com.magneto.mutantebackend.services.MutanteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class MutanteBackendApplicationTests {

	@Autowired
	MutanteController mutanteController;

	@Test
	void contextLoads() {

		String[] arrayDNA = {
			"ATGCGATTBG",
			"CTGTACTCAA",
			"TTATGGAAGG",
			"AGAATGTTGG",
			"CTCCTACCTA",
			"TCACTAGCTT",
			"CACGTAAGTT",
			"GCGAACCCCT",
			"AACCTGACAA",
			"CCGTCCTTTA",
			"GCTAACAGCT",
			"GGTAACAGCT"
		};

		String[] arrayDNA1 = {
			"ATGCGATTBG",
			"CTGTACTCAA",
			"TTATGGAAGG",
			"AGAATGTTGG",
			"CTCCGACCTA",
			"TCACTAGCTT",
			"CACGTAAGTT",
			"GCGAACCCCT",
			"AACCTGACAA",
			"CCGTCCTTTA",
			"GCTAACAGCT",
			"GGTAACAGCT"
		};

		// Test mutante

		try {		
			SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
			secuenciaADNDto.setDna(arrayDNA);

			ResponseEntity<?> responseEntity = mutanteController.detectarMutante(secuenciaADNDto);

			System.out.println("detectarMutante: " + responseEntity.getStatusCode().value() + " " + responseEntity.getStatusCode().getReasonPhrase());
		} catch (ResponseStatusException ex) {
			System.out.println("detectarMutante: " + ex.getMessage());
		}

		// Test no mutante

		try {		
			SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
			secuenciaADNDto.setDna(arrayDNA1);

			ResponseEntity<?> responseEntity = mutanteController.detectarMutante(secuenciaADNDto);

			System.out.println("detectarMutante: " + responseEntity.getStatusCode().value() + " " + responseEntity.getStatusCode().getReasonPhrase());
		} catch (ResponseStatusException ex) {
			System.out.println("detectarMutante: " + ex.getMessage());
		}


		// Test estadisticas

		ResponseEntity<?> responseEntity = mutanteController.consultarEstadisticas();

		System.out.println("consultarEstadisticas: " + ((EstadisticaDto)responseEntity.getBody()).toString());
		
		
	}

}
