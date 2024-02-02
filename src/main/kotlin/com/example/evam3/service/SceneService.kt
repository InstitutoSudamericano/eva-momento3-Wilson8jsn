package com.example.evam3.service


import com.example.evam3.init.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
class SceneService {

    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene{
        try {
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Nombres no debe ser vacio")
            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
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
        try {
            val response = sceneRepository.findById(scene.id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")

            response.apply {
                description = scene.description
            }

            return sceneRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
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

    fun listById(id: Long?): Scene? {
        return sceneRepository.findById(id)
    }


}