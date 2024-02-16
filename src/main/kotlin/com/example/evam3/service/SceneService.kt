package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var filmRepository: FilmRepository
    fun list (): List<Scene> {
        return sceneRepository.findAll()
    }

    fun listById(id: Long?): Scene? {
        return sceneRepository.findById(id)
    }
/*
    fun save(scene: Scene ): Scene {
        try {
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo Descripción no debe ser vacio")

            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }
*/

    fun save(scene: Scene): Scene {
        try {
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw IllegalArgumentException("La descripción no puede estar vacía")
            scene.hours?.takeIf { it > 0 }
                ?: throw IllegalArgumentException("La duración de la escena debe ser mayor que cero")
            val film = filmRepository.findById(scene.filmId)
            val allScenes = sceneRepository.findAllByFilmId(scene.filmId)
            var totalDuration = scene.hours
            allScenes.forEach { totalDuration = totalDuration?.plus(it.hours ?: 0) }
            if (totalDuration ?: 0 > film?.duration ?: 0) {
                throw IllegalArgumentException("La duración total de las escenas excede la duración de la película")
            }
            return sceneRepository.save(scene)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error inesperado")
        }
    }

    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(scene: Scene): Scene {
        //try {
        sceneRepository.findById(scene.id)
            ?: throw Exception("ID no existe")
        return sceneRepository.save(scene)
        //} catch (ex: Exception) {
        //   throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        //  }
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}