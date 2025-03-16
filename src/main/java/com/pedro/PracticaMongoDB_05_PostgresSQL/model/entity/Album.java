package com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('album_id_seq')")
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    @JsonBackReference
    private Grupo grupo;

    @Size(max = 255)
    @NotNull
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "data_lanzamento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLanzamento;

    @Column(name = "puntuacion", precision = 3, scale = 1)
    private BigDecimal puntuacion;

    public Album() {
    }

    public Album(Integer id, Grupo grupo, String titulo, LocalDate dataLanzamento, BigDecimal puntuacion) {
        this.id = id;
        this.grupo = grupo;
        this.titulo = titulo;
        this.dataLanzamento = dataLanzamento;
        this.puntuacion = puntuacion;
    }

    public Album(Grupo grupo, String titulo, LocalDate dataLanzamento, BigDecimal puntuacion) {
        this.grupo = grupo;
        this.titulo = titulo;
        this.dataLanzamento = dataLanzamento;
        this.puntuacion = puntuacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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