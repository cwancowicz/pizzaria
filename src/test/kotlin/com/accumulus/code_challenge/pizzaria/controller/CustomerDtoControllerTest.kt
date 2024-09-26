package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import com.accumulus.code_challenge.pizzaria.service.CustomerService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus

class CustomerDtoControllerTest {

  private val customerService = mockk<CustomerService>()
  private val customerDtoController = CustomerDtoController(customerService)

  @Test
  fun shouldCreateCustomerControllerDto() {
    val customerDto = CustomerDto(email = "test@gmail.com", toppings = mutableListOf("mushrooms"))
    every { customerService.createCustomerDto(customerDto) } answers { customerDto }
    val returnVal = customerDtoController.createCustomerDto(customerDto)

    assertEquals(customerDto, returnVal.body)
    assertEquals(HttpStatus.OK, returnVal.statusCode)
  }

  @Test
  fun shouldGetCustomerControllerDto() {
    val customerDto = CustomerDto(email = "test@gmail.com", toppings = mutableListOf("mushrooms"))
    val email = "test@gmail.com"
    every { customerService.getCustomerDto(email) } answers { customerDto }
    val returnVal = customerDtoController.getCustomerDto(email)

    assertEquals(customerDto, returnVal.body)
    assertEquals(HttpStatus.OK, returnVal.statusCode)
  }

  @Test
  fun shouldUpdateCustomerControllerDto() {
    val customerDto = CustomerDto(email = "test@gmail.com", toppings = mutableListOf("mushrooms"))
    every { customerService.updateCustomerDto(customerDto) } answers { customerDto }
    val returnVal = customerDtoController.updateCustomerDto(customerDto)

    assertEquals(customerDto, returnVal.body)
    assertEquals(HttpStatus.OK, returnVal.statusCode)
  }

  @Test
  fun shouldThrowExceptionWhenCreatingCustomerDtoInvalid() {
    val customerDto = CustomerDto(email = "test", toppings = mutableListOf("mushrooms"))
    assertThrows<GeneralCustomerException> { customerDtoController.createCustomerDto(customerDto) }
  }

  @Test
  fun shouldThrowExceptionWhenUpdatingCustomerDtoInvalid() {
    val customerDto = CustomerDto(email = "", toppings = mutableListOf("mushrooms"))
    assertThrows<GeneralCustomerException> { customerDtoController.updateCustomerDto(customerDto) }
  }
}
