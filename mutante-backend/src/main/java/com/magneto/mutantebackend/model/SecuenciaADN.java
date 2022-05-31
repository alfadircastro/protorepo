package com.magneto.mutantebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "secuencias_adn")
public class SecuenciaADN {
    @Id
    private String id;

    // final para no alterar los registros de secuencias adn registrados en la bd
    private final String[] dna;
    private final Boolean mutant_dna;

    public SecuenciaADN(String[] dna, Boolean mutant_dna) {
        this.dna = dna;
        this.mutant_dna = mutant_dna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getDna() {
        return dna;
    }

    public Boolean getMutant_dna() {
        return mutant_dna;
    }



}