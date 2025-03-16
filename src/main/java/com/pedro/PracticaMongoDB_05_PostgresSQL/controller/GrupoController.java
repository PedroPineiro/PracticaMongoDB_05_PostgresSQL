package com.pedro.PracticaMongoDB_05_PostgresSQL.controller;

import com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions.IdException;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Album;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Grupo;
import com.pedro.PracticaMongoDB_05_PostgresSQL.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postgres/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    @Autowired
    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/crearPostgres")
    public ResponseEntity<String> crearGrupo(@RequestBody Grupo grupo) {
        try {
            grupoService.crearGrupo(grupo);
            return ResponseEntity.ok().body("Grupo creado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el grupo: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> listarGrupos() {
        try {
            List<Grupo> grupos = grupoService.listarGrupos();
            return ResponseEntity.ok().body(grupos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Grupo> listarGrupoPorId(@PathVariable Integer id) {
        try {
            Grupo grupo = grupoService.obtenerGrupo(id);
            return ResponseEntity.ok().body(grupo);
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarGrupo(@PathVariable Integer id, @RequestBody Grupo grupo) {
        try {
            grupoService.actualizarGrupo(id, grupo);
            return ResponseEntity.ok().body("Grupo actualizado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrarPostgres/{id}")
    public ResponseEntity<String> borrarGrupo(@PathVariable Integer id) {
        try {
            grupoService.eliminarGrupo(id);
            return ResponseEntity.ok().body("Grupo borrado correctamente");
        } catch (IdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ServiceMongo

    /**
     * Segundo metodo para crear el objeto en postgres pero usando una llamada a mongoService
     * @param grupo el objeto a insertar en las bases de datos
     * @return un mensaje indicando si se creó o no
     */
    @PostMapping("/crear")
    public ResponseEntity<String> createGrupoLlmadaPostgreSQLController(@RequestBody Grupo grupo) {
        try{
            grupoService.createGrupoService(grupo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Grupo creado correctamente en llamada");
    }

    /**
     * Metodo para borrar un grupo por id en postgres y llamar a mongoService para que lo borre
     * @param id el id del grupo
     * @return un mensaje indicando si se borró o no el registro
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarGrupoByIdLlamadaPostgreSQLController(@PathVariable Integer id) {
        try{
            boolean eliminado = grupoService.borrarGrupoByIdService(id);
            if(!eliminado){
                return ResponseEntity.badRequest().body("Grupo no encontrado");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Grupo borrado correctamente en llamada");
    }
}