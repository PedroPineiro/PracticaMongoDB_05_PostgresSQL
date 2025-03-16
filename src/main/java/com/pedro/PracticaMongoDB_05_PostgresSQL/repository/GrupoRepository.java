package com.pedro.PracticaMongoDB_05_PostgresSQL.repository;

import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la clase de grupos
 * @author pedro
 * @version 1.0
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    Grupo findByid(Integer grupoID);
}