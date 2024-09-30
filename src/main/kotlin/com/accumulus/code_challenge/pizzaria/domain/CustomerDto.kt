package com.accumulus.code_challenge.pizzaria.domain

data class CustomerDto(
  val email: String,
  val toppings: List<String>,
  val receivePromos: Boolean = false,
  val delivery: Boolean = false
)
