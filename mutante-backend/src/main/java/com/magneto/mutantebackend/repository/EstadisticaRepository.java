package com.magneto.mutantebackend.repository;

import java.util.Optional;

import com.magneto.mutantebackend.model.Estadistica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EstadisticaRepository extends MongoRepository<Estadistica, String> {
    @Query(value="{stat_id: '?0'}")
    Optional<Estadistica> findEstadisticaByStat_id(Long stat_id);
    
}