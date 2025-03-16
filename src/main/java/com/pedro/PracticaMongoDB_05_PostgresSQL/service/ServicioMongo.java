package com.pedro.PracticaMongoDB_05_PostgresSQL.service;

import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Album;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Grupo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "nhanhelachupa", url = "http://localhost:8081")
public interface ServicioMongo {

    @PostMapping("/mongo/grupos/crear")
    void crearGrupoLlamada(@RequestBody Grupo grupo);

    @DeleteMapping("/mongo/grupos/borrar/{id}")
    void borrarGrupoLlamada(@PathVariable Integer id);

    @PostMapping("/mongo/albums/crear")
    void crearAlbum(@RequestBody Album album);

    @DeleteMapping("/mongo/albums/borrar/{id}")
    void borrarAlbumLlamada(@PathVariable Integer id);
}