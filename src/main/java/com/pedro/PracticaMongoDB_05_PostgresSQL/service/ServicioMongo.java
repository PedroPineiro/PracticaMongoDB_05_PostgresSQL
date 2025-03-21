package com.pedro.PracticaMongoDB_05_PostgresSQL.service;

import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.GrupoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mongo", url = "http://localhost:8081")
public interface ServicioMongo {

    @PostMapping("/mongo/grupos/crear")
    void crearGrupoLlamada(@RequestBody GrupoDTO grupoDTO);

    @DeleteMapping("/mongo/grupos/borrar/{id}")
    void borrarGrupoLlamada(@PathVariable Integer id);

    @PostMapping("/mongo/albums/crear")
    void crearAlbum(@RequestBody AlbumDTO albumDTO);

    @DeleteMapping("/mongo/albums/borrar/{id}")
    void borrarAlbumLlamada(@PathVariable Integer id);
}