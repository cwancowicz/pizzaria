package com.accumulus.code_challenge.pizzaria.validator

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

class CustomerDtoValidatorTest {

  @Test
  fun shouldPassValidationWhenCustomerDtoIsValid() {
    val customerDto = CustomerDto(email = "test@gmail.com", listOf("mushrooms"))
    CustomerDtoValidator.validate(customerDto)
  }

  @Test
  fun shouldThrowExceptionWhenEmailIsInvalid() {
    val customerDto = CustomerDto(email = "123-fake", listOf("peppers"))
    assertFailsWith<GeneralCustomerException> { CustomerDtoValidator.validate(customerDto) }
  }

  @Test
  fun shouldThrowExceptionWhenToppingsSizeGreaterThanFifty() {
    val customerDto = CustomerDto(email = "123-fake", (1..51).map { "item $it" })
    assertFailsWith<GeneralCustomerException> { CustomerDtoValidator.validate(customerDto) }
  }

  @Test
  fun shouldThrowExceptionWhenToppingsAreEmpty() {
    val customerDto = CustomerDto(email = "123-fake", emptyList())
    assertFailsWith<GeneralCustomerException> { CustomerDtoValidator.validate(customerDto) }
  }
}
