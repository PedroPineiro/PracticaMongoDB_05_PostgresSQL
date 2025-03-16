package com.pedro.PracticaMongoDB_05_PostgresSQL.repository;

import com.pedro.PracticaMongoDB_05_PostgresSQL.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la clase de albums
 * @author pedro
 * @version 1.0
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
