package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    @Autowired
    lateinit var sceneRepository: SceneRepository
    fun list(): List<Character> {
        return characterRepository.findAll()
    }

    fun listById(id: Long?): Character? {
        return characterRepository.findById(id)
    }

    fun save(character: Character): Character{
        try{
            val scene = sceneRepository.findById(character.sceneId)
            val listCharacter = characterRepository.findBySceneId(character.sceneId)
            var sum = 0.0

            listCharacter.map {
                sum += sum * it.cost
            }

            if (scene?.budget!! <= sum + character.cost) {
                throw IllegalArgumentException("El costo total de los personajes supera el presupuesto de la escena")
            }

            return characterRepository.save(character)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(character: Character): Character {
        try {
            characterRepository.findById(character.id)
                ?: throw Exception("El Id no existe")

            return characterRepository.save(character)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(character: Character): Character {
        //try {
        characterRepository.findById(character.id)
            ?: throw Exception("ID no existe")
        return characterRepository.save(character)
        //} catch (ex: Exception) {
        //   throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        //  }
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}