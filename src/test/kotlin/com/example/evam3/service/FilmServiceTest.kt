package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class FilmServiceTest {

    @SpringBootTest
    internal class FilmServiceTest {

        @Autowired
        lateinit var filmService: FilmService

        @MockBean
        lateinit var filmRepository: FilmRepository

        val filmMock = Film().apply {
            id = 1
            title = "La Momia"
            director = "Director de Prueba"
            duration = 120
        }

        @Test
        fun listFilms() {

            Mockito.`when`(filmRepository.findAll()).thenReturn(listOf(filmMock))


            val films = filmService.list()


            Assertions.assertEquals(1, films.size)
            Assertions.assertEquals(filmMock.title, films[0].title)
            Assertions.assertEquals(filmMock.director, films[0].director)
            Assertions.assertEquals(filmMock.duration, films[0].duration)
        }

        @Test
        fun saveFilm() {
            Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)

            val savedFilm = filmService.save(filmMock)
            Assertions.assertEquals(filmMock.id, savedFilm.id)
            Assertions.assertEquals(filmMock.title, savedFilm.title)
            Assertions.assertEquals(filmMock.director, savedFilm.director)
            Assertions.assertEquals(filmMock.duration, savedFilm.duration)
        }
    }

}