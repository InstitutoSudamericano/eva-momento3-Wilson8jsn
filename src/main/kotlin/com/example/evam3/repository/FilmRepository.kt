package com.example.evam3.repository



import com.example.evam3.init.Film
import org.springframework.data.jpa.repository.JpaRepository


interface FilmRepository : JpaRepository<Film, Long> {
    fun findById (id: Long?): Film?
}