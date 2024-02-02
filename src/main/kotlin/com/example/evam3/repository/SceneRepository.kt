package com.example.evam3.repository



import com.example.evam3.init.Scene
import org.springframework.data.jpa.repository.JpaRepository

interface SceneRepository : JpaRepository<Scene, Long> {
    fun findById (id: Long?): Scene?
}

