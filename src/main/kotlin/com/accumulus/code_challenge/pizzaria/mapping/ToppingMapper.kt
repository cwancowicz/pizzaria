package com.accumulus.code_challenge.pizzaria.mapping

import com.accumulus.code_challenge.pizzaria.entity.Customer
import com.accumulus.code_challenge.pizzaria.entity.Topping

class ToppingMapper {

  companion object {

    fun map(toppings: List<String>, customer: Customer): MutableList<Topping> {
      return toppings.map { Topping(name = it, customer = customer) }.toMutableList()
    }

    fun map(toppings: List<Topping>): List<String> {
      return toppings.map { it.name }
    }
  }
}
