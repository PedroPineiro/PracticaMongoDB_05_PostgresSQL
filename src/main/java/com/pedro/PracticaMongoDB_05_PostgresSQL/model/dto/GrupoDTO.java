package com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GrupoDTO {

    private String nome;
    private String xenero;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFormacion;

    public GrupoDTO() {
    }

    public GrupoDTO(String nome, String xenero, LocalDate dstaFormacion) {
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dstaFormacion;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getXenero() {
        return xenero;
    }

    public void setXenero(String xenero) {
        this.xenero = xenero;
    }

    public LocalDate getDataFormacion() {
        return dataFormacion;
    }

    public void setDataFormacion(LocalDate dataFormacion) {
        this.dataFormacion = dataFormacion;
    }
}
