package com.magneto.mutantebackend.controller;

import java.util.List;

import com.magneto.mutantebackend.dto.EstadisticaDto;
import com.magneto.mutantebackend.dto.MessageResponseDto;
import com.magneto.mutantebackend.dto.SecuenciaADNDto;
import com.magneto.mutantebackend.services.MutanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class MutanteController {

    @Autowired
    MutanteService mutanteService;

    @PostMapping("/mutant")
	public ResponseEntity<?> detectarMutante(@RequestBody SecuenciaADNDto secuenciaADN) {

        Boolean isMutant = mutanteService.isMutant(secuenciaADN);

        if (isMutant) {
            return ResponseEntity.ok(null);          
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
        }

	}

    @PostMapping("/stats")
	public ResponseEntity<?> consultarEstadisticas() {

        EstadisticaDto estadisticaDto = mutanteService.consultarEstadisticas();

        if (estadisticaDto != null) {
            return ResponseEntity.ok(estadisticaDto);
        }
        else {
            return ResponseEntity
					.badRequest()
					.body(new MessageResponseDto("ERROR: Se presenta un error al generar las estadisticas."));
        }

	}

}