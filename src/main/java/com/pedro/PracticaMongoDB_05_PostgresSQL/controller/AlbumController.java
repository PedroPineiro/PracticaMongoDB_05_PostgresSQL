package com.pedro.PracticaMongoDB_05_PostgresSQL.controller;

import com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions.IdException;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Album;
import com.pedro.PracticaMongoDB_05_PostgresSQL.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postgres/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/crearPostgres")
    public ResponseEntity<String> crearAlbum(@RequestBody Album album) {
        try {
            albumService.crearAlbum(album);
            return ResponseEntity.ok().body("Álbum creado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el álbum: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Album>> listarAlbumes() {
        try {
            List<Album> albumes = albumService.listarAlbumes();
            return ResponseEntity.ok().body(albumes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Album> listarAlbumPorId(@PathVariable Integer id) {
        try {
            Album album = albumService.listarAlbumPorId(id);
            return ResponseEntity.ok().body(album);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAlbum(@PathVariable Integer id, @RequestBody Album album) {
        try {
            albumService.actualizarAlbum(id, album);
            return ResponseEntity.ok().body("Álbum actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrarPostgres/{id}")
    public ResponseEntity<String> borrarAlbum(@PathVariable Integer id) {
        try {
            albumService.eliminarAlbum(id);
            return ResponseEntity.ok().body("Álbum borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Metodos de ServicioMongo

    /**
     * Metodo para crear un album en postgreSQL y llamar a mongoService
     * @param albumDTO el album DTO a crear en postgreSQL y mongoService
     * @return un mensaje indicando si se creo o no
     */
    @PostMapping("/crearMongo")
    public ResponseEntity<String> createNewAlbumLlamadaPostgreSQLController(@RequestBody AlbumDTO albumDTO) {
        try {
            albumService.createAlbumService(albumDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Album creado correctamente en llamada");
    }

    /**
     * Metodo para borrar un album en postgreSQL y mongoService
     * @param id el id del album a borrar en ambas bases de datos
     * @return un mensaje indicando si se borró o no
     */
    @DeleteMapping("/borrarMongo/{id}")
    public ResponseEntity<String> borrarAlbumByIdLlamadaPostgreSQLController(@PathVariable Integer id) {
        try{
            boolean eliminado = albumService.borrarAlbumByIdService(id);
            if(!eliminado){
                return ResponseEntity.badRequest().body("Album no encontrado");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Album borrado correctamente en llamada");
    }
}