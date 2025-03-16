package com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('grupo_id_seq')")
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "xenero", length = 100)
    private String xenero;

    @Column(name = "data_formacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFormacion;

    @OneToMany(mappedBy = "grupo")
    @JsonManagedReference
    @JsonIgnore
    private List<Album> albumList = new ArrayList<>();

    public Grupo() {
    }

    public Grupo(Integer id, String nome, String xenero, LocalDate dataFormacion, List<Album> albumList) {
        this.id = id;
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dataFormacion;
        this.albumList = albumList;
    }

    public Grupo(String nome, String xenero, LocalDate dataFormacion) {
        this.nome = nome;
        this.xenero = xenero;
        this.dataFormacion = dataFormacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

}