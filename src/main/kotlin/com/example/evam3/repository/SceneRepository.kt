package com.example.evam3.repository

import com.example.evam3.entity.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository:JpaRepository<Scene, Long> {
    fun findById (id: Long?): Scene?
        fun findByDescription(tittle: String?): List<Scene>
        @Query("SELECT * FROM scene WHERE film_id = :filmId", nativeQuery = true)
        fun findAllByFilmId(@Param("filmId") filmId: Long?): List<Scene>
}