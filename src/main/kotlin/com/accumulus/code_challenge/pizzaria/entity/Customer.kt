package com.accumulus.code_challenge.pizzaria.entity

import jakarta.persistence.*

@Entity
data class Customer(
  @Id @GeneratedValue var pid: Long? = null,
  @Column(nullable = false, unique = true) var email: String,
  @OneToMany(
    mappedBy = "customer",
    cascade = [CascadeType.ALL],
    fetch = FetchType.LAZY,
    orphanRemoval = true)
  var toppings: MutableList<Topping> = ArrayList(),
  var receivePromos: Boolean = false,
  var delivery: Boolean = false
)
