package com.accumulus.code_challenge.pizzaria.mapper

import com.accumulus.code_challenge.pizzaria.entity.Customer
import com.accumulus.code_challenge.pizzaria.entity.Topping
import com.accumulus.code_challenge.pizzaria.mapping.ToppingMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ToppingMapperTest {

  @Test
  fun shouldMapToppingNamesToToppings() {
    val toppingNames = mutableListOf("onion")
    val customer =
      Customer(email = "test@gmail.com", toppings = mutableListOf(Topping(name = "onion")))

    val toppings = ToppingMapper.map(toppingNames, customer)
    assertEquals(1, toppings.size)
    assertEquals(customer, toppings.get(0).customer)
    assertEquals("onion", toppings.get(0).name)
  }

  @Test
  fun shouldMapToppingsToToppingNames() {
    val toppings = mutableListOf(Topping(name = "peppers"))
    val toppingNames = ToppingMapper.map(toppings)

    assertEquals(1, toppingNames.size)
    assertEquals("peppers", toppingNames.get(0))
  }
}
