package com.example.evam3.entity

import jakarta.persistence.*


@Entity
@Table(name = "scene")
class Scene {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null

    @Column(name = "description", nullable = false, length = 100)
    var description: String? = null
    var budget: Double = 0.0
    @Column(name = "hours", nullable = false)
    var hours: Int? = null
    @Column(name = "film_id")
    var filmId: Long? = null

}
