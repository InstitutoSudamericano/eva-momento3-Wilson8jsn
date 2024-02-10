package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SceneServiceTest {

    @InjectMocks
    lateinit var sceneService: SceneService

    @Mock
    lateinit var sceneRepository: SceneRepository

    val sceneMock = Scene().apply {
        id = 1
        description = "Sample Scene"
        budget = 10000.0
        hours = 2
        filmId = 1
    }

    @Test
    fun listScenes() {
        val sceneList = listOf(sceneMock)
        Mockito.`when`(sceneRepository.findAll()).thenReturn(sceneList)

        val result = sceneService.list()

        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(sceneMock.id, result[0].id)
    }

    @Test
    fun saveSceneCorrect() {
        Mockito.`when`(sceneRepository.save(Mockito.any(Scene::class.java))).thenReturn(sceneMock)

        val response = sceneService.save(sceneMock)

        Assertions.assertEquals(sceneMock.id, response.id)
    }

    @Test
    fun saveSceneWhenDescriptionIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            sceneMock.apply { description = " " }

            Mockito.`when`(sceneRepository.save(Mockito.any(Scene::class.java))).thenReturn(sceneMock)

            sceneService.save(sceneMock)
        }
    }
}
