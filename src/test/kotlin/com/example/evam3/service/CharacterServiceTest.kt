package com.example.evam3.service



import com.example.evam3.entity.Character
import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class CharacterServiceTest {

    @InjectMocks
    lateinit var characterService: CharacterService

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Mock
    lateinit var sceneRepository: SceneRepository

    val characterMock = Character().apply {
        id = 1
        description = "Descripción del personaje"
        cost = 50.75
        interpreted = "Interpretación del personaje"
        age = 25
        sceneId = 1
    }



    @Test
    fun saveCharacterWhenDescriptionIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            characterMock.apply { description = " " }


            Mockito.`when`(characterRepository.save(Mockito.any(Character::class.java))).thenReturn(characterMock)

            characterService.save(characterMock)
        }
    }



    @Test
    fun listCharacters() {
        val characterList = listOf(characterMock)
        Mockito.`when`(characterRepository.findAll()).thenReturn(characterList)
        val response = characterService.list()
        Assertions.assertEquals(response, characterList)
    }
}
