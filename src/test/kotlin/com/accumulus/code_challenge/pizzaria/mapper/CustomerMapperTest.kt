package com.accumulus.code_challenge.pizzaria.mapper

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.entity.Customer
import com.accumulus.code_challenge.pizzaria.entity.Topping
import com.accumulus.code_challenge.pizzaria.mapping.CustomerMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CustomerMapperTest {

  @Test
  fun shouldMapCustomerDtoToCustomerEntity() {
    val customerDto =
      CustomerDto(
        email = "user@gmail.com",
        listOf("peppers", "mushroom"),
        receivePromos = true,
        delivery = true)

    val customer = CustomerMapper.map(customerDto)
    assertEquals(customerDto.email, customer.email)
    assertEquals(2, customer.toppings.size)
    assertEquals("peppers", customer.toppings.get(0).name)
    assertEquals("mushroom", customer.toppings.get(1).name)
    assertTrue(customer.receivePromos)
    assertTrue(customer.delivery)
  }

  @Test
  fun shouldMapCustomerEntityToCustomerDto() {
    val customer =
      Customer(
        email = "user@gmail.com",
        toppings = mutableListOf(Topping(name = "mushroom")),
        receivePromos = true,
        delivery = true)
    val customerDto = CustomerMapper.map(customer)

    assertEquals(customer.email, customerDto.email)
    assertEquals(1, customerDto.toppings.size)
    assertEquals("mushroom", customerDto.toppings.get(0))
    assertTrue(customerDto.receivePromos)
    assertTrue(customerDto.delivery)
  }
}
