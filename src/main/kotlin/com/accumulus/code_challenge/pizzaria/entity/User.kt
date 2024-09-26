package com.accumulus.code_challenge.pizzaria.entity

import jakarta.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val email: String,

    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val toppings: List<Topping> = emptyList()
)
