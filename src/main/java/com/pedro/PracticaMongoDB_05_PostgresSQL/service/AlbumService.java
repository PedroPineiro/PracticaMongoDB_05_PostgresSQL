package com.pedro.PracticaMongoDB_05_PostgresSQL.service;

import com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions.IdException;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Album;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.Grupo;
import com.pedro.PracticaMongoDB_05_PostgresSQL.repository.AlbumRepository;
import com.pedro.PracticaMongoDB_05_PostgresSQL.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final GrupoRepository grupoRepository;
    private final ServicioMongo servicioMongo;

    public AlbumService(AlbumRepository albumRepository, GrupoRepository grupoRepository, ServicioMongo servicioMongo) {
        this.albumRepository = albumRepository;
        this.grupoRepository = grupoRepository;
        this.servicioMongo = servicioMongo;
    }

    public void crearAlbum(Album album) {
        if (!grupoRepository.existsById(album.getGrupo().getId())) {
            throw new IdException("El ID del grupo no existe: " + album.getGrupo().getId());
        }
        albumRepository.save(album);
    }

    public List<Album> listarAlbumes() {
        return albumRepository.findAll();
    }

    public Album listarAlbumPorId(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new IdException("No se encontró un álbum con el ID: " + id));
    }

    public void actualizarAlbum(Integer id, Album album) {
        if (!albumRepository.existsById(id)) {
            throw new IdException("No se puede actualizar, el ID del álbum no existe: " + id);
        }
        album.setId(id); // Asegurar que el ID no cambie
        albumRepository.save(album);
    }

    public void eliminarAlbum(Integer id) {
        if (!albumRepository.existsById(id)) {
            throw new IdException("No se puede eliminar, el ID del álbum no existe: " + id);
        }
        albumRepository.deleteById(id);
    }

    // Metodos de ServicioMongo

    public void createAlbumService(Album album) {
        // Guardar el álbum en PostgreSQL
        albumRepository.save(album);

        // Llamar al Feign Client para guardar el álbum en MongoDB
        servicioMongo.crearAlbum(album);
    }

    public boolean borrarAlbumByIdService(Integer id) {
        if(!albumRepository.existsById(id)) {
            return false;
        }
        albumRepository.deleteById(id);
        servicioMongo.borrarAlbumLlamada(id);
        return true;
    }
}