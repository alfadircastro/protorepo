package com.magneto.mutantebackend.repository;

import java.util.List;

import com.magneto.mutantebackend.model.SecuenciaADN;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuenciaADNRepository extends MongoRepository<SecuenciaADN, String> {
    
    @Query(value="{mutant_dna: ?0}")
    List<SecuenciaADN> findSecuenciaADNByMutant_dna(Boolean mutant_dna);

    public long count();
}