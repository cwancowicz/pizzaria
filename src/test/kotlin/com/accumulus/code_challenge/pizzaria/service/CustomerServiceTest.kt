package com.accumulus.code_challenge.pizzaria.service

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.entity.Customer
import com.accumulus.code_challenge.pizzaria.entity.Topping
import com.accumulus.code_challenge.pizzaria.entity.ToppingCount
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import com.accumulus.code_challenge.pizzaria.repositories.CustomerRepository
import com.accumulus.code_challenge.pizzaria.repositories.ToppingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class CustomerServiceTest {

  private val customerRepository = mockk<CustomerRepository>()
  private val toppingRepository = mockk<ToppingRepository>()
  val customerDtoService = CustomerService(customerRepository, toppingRepository)

  @Test
  fun shouldCreateCustomerDto() {

    val customerDto = CustomerDto(email = "test@gmail.com", listOf("mushrooms"))
    val customer =
      Customer(email = "test@gmail.com", toppings = mutableListOf(Topping(name = "mushrooms")))
    every { customerRepository.existsByEmail("test@gmail.com") } answers { false }
    every { customerRepository.save(any()) } answers { customer }

    val returnVal = customerDtoService.createCustomerDto(customerDto)
    assertEquals(expected = "test@gmail.com", actual = returnVal.email)
    assertTrue(customerDto.toppings.size == 1)
    assertEquals(expected = "mushrooms", actual = customerDto.toppings.get(0))
  }

  @Test
  fun shouldThrowExceptionWhenCreateCustomerDtoAndAlreadyExists() {

    val customerDto = CustomerDto(email = "test@gmail.com", listOf("mushrooms"))

    every { customerRepository.existsByEmail("test@gmail.com") } answers { true }
    assertThrows<GeneralCustomerException> { customerDtoService.createCustomerDto(customerDto) }
  }

  @Test
  fun shouldUpdateExistingCustomerDto() {
    val customerDto = CustomerDto(email = "test@gmail.com", listOf("mushrooms"))
    val customer =
      Customer(email = "test@gmail.com", toppings = mutableListOf(Topping(name = "mushrooms")))
    every { customerRepository.findByEmail("test@gmail.com") } answers { customer }
    every { customerRepository.save(any()) } answers { customer }

    val returnVal = customerDtoService.updateCustomerDto(customerDto)
    assertEquals(expected = "test@gmail.com", actual = returnVal.email)
    assertTrue(customerDto.toppings.size == 1)
    assertEquals(expected = "mushrooms", actual = customerDto.toppings.get(0))
  }

  @Test
  fun shouldThrowExceptionWhenCustomerDtoDoesNotExist() {
    val customerDto = CustomerDto(email = "test@gmail.com", listOf("mushrooms"))

    every { customerRepository.findByEmail("test@gmail.com") } answers { null }
    assertThrows<GeneralCustomerException> { customerDtoService.updateCustomerDto(customerDto) }
  }

  @Test
  fun shouldGetToppingCounts() {
    val pageable = mockk<Pageable>()
    val page = PageImpl(mutableListOf(ToppingCount("mushrooms", 300)))
    every { toppingRepository.getToppingCounts(pageable) } answers { page }

    val returnVal = customerDtoService.getToppingCount(pageable)
    assertEquals(1, returnVal.content.size)
  }

  @Test
  fun shouldGetCustomerDto() {
    val customer =
      Customer(email = "test@gmail.com", toppings = mutableListOf(Topping(name = "mushrooms")))
    every { customerRepository.findByEmail("test@gmail.com") } answers { customer }
    val returnVal = customerDtoService.getCustomerDto("test@gmail.com")

    assertEquals(expected = "test@gmail.com", actual = returnVal.email)
    assertEquals(expected = 1, actual = returnVal.toppings.size)
  }

  @Test
  fun shouldThrowExceptionWhenGetCustomerDtoDoesNotExist() {
    every { customerRepository.findByEmail("test@gmail.com") } answers { null }
    assertThrows<GeneralCustomerException> { customerDtoService.getCustomerDto("test@gmail.com") }
  }

  @Test
  fun shouldGetAllCustomersByReceivePromos() {
    val pageable = mockk<Pageable>()
    val page = PageImpl(mutableListOf(Customer(email = "test@gmail.com")))
    every { customerRepository.findAllByReceivePromos(true, pageable) } answers { page }
    customerDtoService.getCustomerDtos(pageable, "receivePromos", true)
    verify { customerRepository.findAllByReceivePromos(true, pageable) }
  }

  @Test
  fun shouldGetAllCustomersByDelivery() {
    val pageable = mockk<Pageable>()
    val page = PageImpl(mutableListOf(Customer(email = "test@gmail.com")))
    every { customerRepository.findAllByDelivery(true, pageable) } answers { page }
    customerDtoService.getCustomerDtos(pageable, "delivery", true)
    verify { customerRepository.findAllByDelivery(true, pageable) }
  }
}
