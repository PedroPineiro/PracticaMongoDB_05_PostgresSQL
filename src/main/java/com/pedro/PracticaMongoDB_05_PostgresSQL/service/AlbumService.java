package com.pedro.PracticaMongoDB_05_PostgresSQL.service;

import com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions.IdException;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Album;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Grupo;
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

    /**
     * Metodo para crear un album y que se cree en mongoService
     *
     * @param albumDTO el ablum DTO a crear
     */

    public void createAlbumService(AlbumDTO albumDTO) {
        // Obtener el grupo por su ID
        Grupo grupo = grupoRepository.findById(albumDTO.getGrupoID())
                .orElseThrow(() -> new IdException("El grupo con el ID " + albumDTO.getGrupoID() + " no existe"));

        // Crear el álbum
        Album album = new Album(grupo, albumDTO.getTitulo(), albumDTO.getDataLanzamento(), albumDTO.getPuntuacion());

        // Guardar el álbum en PostgreSQL
        albumRepository.save(album);  // Aquí se genera el ID automáticamente

        // Asignar el ID generado al DTO
        albumDTO.setId(album.getId());  // Ahora el ID no es nulo

        // Llamar al servicio de MongoDB
        servicioMongo.crearAlbum(albumDTO);
    }

    /**
     * metodo para borrar un album por id en postgreSQL y mongoService
     *
     * @param id el id del album
     * @return un mensaje indicando si se borro o no
     */
    public boolean borrarAlbumByIdService(Integer id) {
        if (!albumRepository.existsById(id)) {
            return false;
        }
        albumRepository.deleteById(id);
        servicioMongo.borrarAlbumLlamada(id);
        return true;
    }

    /**
     * Metodo para obtener un grupo y saber si existe por su id o no
     *
     * @param albumDTO la DTO para buscar al grupo
     * @return el objeto Grupo
     */
    private Grupo getGrupo(AlbumDTO albumDTO) {
        Grupo grupo = grupoRepository.findByid(albumDTO.getGrupoID());
        if (grupo == null) {
            throw new IdException("El grupo con el id " + albumDTO.getGrupoID() + " no existe");
        }
        return grupo;
    }
}