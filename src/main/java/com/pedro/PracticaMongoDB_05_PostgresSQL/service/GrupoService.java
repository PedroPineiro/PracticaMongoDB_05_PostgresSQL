package com.pedro.PracticaMongoDB_05_PostgresSQL.service;

import com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions.IdException;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.AlbumDTO;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.dto.GrupoDTO;
import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Grupo;
import com.pedro.PracticaMongoDB_05_PostgresSQL.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final ServicioMongo servicioMongo;

    public GrupoService(GrupoRepository grupoRepository, ServicioMongo servicioMongo) {
        this.grupoRepository = grupoRepository;
        this.servicioMongo = servicioMongo;
    }

    public void crearGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    public List<Grupo> listarGrupos() {
        return grupoRepository.findAll();
    }

    public Grupo obtenerGrupo(Integer id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new IdException("No se encontró un grupo con el ID: " + id));
    }

    public void actualizarGrupo(Integer id, Grupo grupo) {
        if (!grupoRepository.existsById(id)) {
            throw new IdException("No se puede actualizar, el ID del grupo no existe: " + id);
        }
        grupo.setId(id); // Asegurar que el ID no cambie
        grupoRepository.save(grupo);
    }

    public void eliminarGrupo(Integer id) {
        if (!grupoRepository.existsById(id)) {
            throw new IdException("No se puede eliminar, el ID del grupo no existe: " + id);
        }
        grupoRepository.deleteById(id);
    }

    // Metodos de ServicioMongo

    public void createGrupoService(GrupoDTO grupoDTO) {
        // Convertir el DTO a entidad
        Grupo grupo = new Grupo();
        grupo.setNome(grupoDTO.getNome());
        grupo.setXenero(grupoDTO.getXenero());
        grupo.setDataFormacion(grupoDTO.getDataFormacion());

        // Guardar el grupo en PostgreSQL
        grupoRepository.save(grupo);

        // Llamar al Feign Client para guardar el grupo en MongoDB
        servicioMongo.crearGrupoLlamada(grupoDTO);
    }

    public boolean borrarGrupoByIdService(Integer id) {
        if (!grupoRepository.existsById(id)) {
            return false;
        }

        // Borrar el grupo en PostgreSQL
        grupoRepository.deleteById(id);

        // Llamar al Feign Client para borrar el grupo en MongoDB
        servicioMongo.borrarGrupoLlamada(id);

        return true;
    }
}