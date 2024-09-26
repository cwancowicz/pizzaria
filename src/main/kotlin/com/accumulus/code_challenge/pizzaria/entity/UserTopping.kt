package com.accumulus.code_challenge.pizzaria.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserTopping(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,



)
