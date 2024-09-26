package com.accumulus.code_challenge.pizzaria.entity

import jakarta.persistence.*

@Entity
data class Topping (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(name = "user_id")
    @JoinColumn(name="user_id", referencedColumnName = "id")
    val userId: Long? = null,

    //    @ManyToOne
//    val user: User? = null

)