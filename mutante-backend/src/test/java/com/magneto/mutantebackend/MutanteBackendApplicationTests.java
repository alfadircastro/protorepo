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
	void testMutante() {

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

		try {		
			SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
			secuenciaADNDto.setDna(arrayDNA);

			ResponseEntity<?> responseEntity = mutanteController.detectarMutante(secuenciaADNDto);

			System.out.println("testMutante: " + responseEntity.getStatusCode().value() + " " + responseEntity.getStatusCode().getReasonPhrase());
		} catch (ResponseStatusException ex) {
			System.out.println("testMutante: " + ex.getMessage());
		}
		
	}

	@Test
	void testNoMutante() {
		String[] arrayDNA = {
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

		try {		
			SecuenciaADNDto secuenciaADNDto = new SecuenciaADNDto();
			secuenciaADNDto.setDna(arrayDNA);

			ResponseEntity<?> responseEntity = mutanteController.detectarMutante(secuenciaADNDto);

			System.out.println("testNoMutante: " + responseEntity.getStatusCode().value() + " " + responseEntity.getStatusCode().getReasonPhrase());
		} catch (ResponseStatusException ex) {
			System.out.println("testNoMutante: " + ex.getMessage());
		}

	}

	@Test
	void testEstadisticas() {

		ResponseEntity<?> responseEntity = mutanteController.consultarEstadisticas();

		System.out.println("testEstadisticas: " + ((EstadisticaDto)responseEntity.getBody()).toString());

	}

}
