package com.magneto.mutantebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estadisticas")
public class Estadistica {
    @Id
    private String id;

    private Long stat_id;
    private Long count_mutant_dna;
    private Long count_human_dna;
    private Float ratio;

    public Estadistica() {
    }

    /**
     * @return Long return the count_mutant_dna
     */
    public Long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    /**
     * @param count_mutant_dna the count_mutant_dna to set
     */
    public void setCount_mutant_dna(Long count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    /**
     * @return Long return the count_human_dna
     */
    public Long getCount_human_dna() {
        return count_human_dna;
    }

    /**
     * @param count_human_dna the count_human_dna to set
     */
    public void setCount_human_dna(Long count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    /**
     * @return Float return the ratio
     */
    public Float getRatio() {
        return ratio;
    }

    /**
     * @param ratio the ratio to set
     */
    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    /**
     * @return Long return the stat_id
     */
    public Long getStat_id() {
        return stat_id;
    }

    /**
     * @param stat_id the stat_id to set
     */
    public void setStat_id(Long stat_id) {
        this.stat_id = stat_id;
    }

}