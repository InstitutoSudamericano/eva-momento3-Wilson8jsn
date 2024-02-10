package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list (): List<Scene> {
        return sceneRepository.findAll()
    }

    fun listById(id: Long?): Scene? {
        return sceneRepository.findById(id)
    }

    fun save(scene: Scene ): Scene {
        try {
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo Descripción no debe ser vacio")

            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
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