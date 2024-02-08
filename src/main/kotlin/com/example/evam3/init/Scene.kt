package com.example.evam3.init

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

    @Column(name = "budget", length = 100)
    var budget: String? = null

    @Column(name = "seconds", nullable = false)
    var seconds: Int? = null

    @Column(name = "movie_id")
    var film_id: Long? = null

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false)
    var movie: Film? = null
}
