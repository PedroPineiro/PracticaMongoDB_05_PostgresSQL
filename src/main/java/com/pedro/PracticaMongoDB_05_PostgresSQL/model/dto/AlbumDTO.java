package com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumDTO {

    private Integer id;
    private Integer grupoID;
    private String titulo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLanzamento;
    private BigDecimal puntuacion;

    public AlbumDTO() {
    }

    public AlbumDTO(Integer id, Integer grupoID, String titulo, LocalDate dataLanzamento, BigDecimal puntuacion) {
        this.id = id;
        this.grupoID = grupoID;
        this.titulo = titulo;
        this.dataLanzamento = dataLanzamento;
        this.puntuacion = puntuacion;
    }

    public AlbumDTO(Integer grupoID, String titulo, LocalDate dataLanzamento, BigDecimal puntuacion) {
        this.grupoID = grupoID;
        this.titulo = titulo;
        this.dataLanzamento = dataLanzamento;
        this.puntuacion = puntuacion;
    }

    // getter y setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Integer grupoID) {
        this.grupoID = grupoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLanzamento() {
        return dataLanzamento;
    }

    public void setDataLanzamento(LocalDate dataLanzamento) {
        this.dataLanzamento = dataLanzamento;
    }

    public BigDecimal getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigDecimal puntuacion) {
        this.puntuacion = puntuacion;
    }
}
