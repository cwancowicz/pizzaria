package com.accumulus.code_challenge.pizzaria.validator

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import org.apache.commons.validator.routines.EmailValidator

class CustomerDtoValidator {

  companion object {
    fun validate(customerDto: CustomerDto) {
      if (customerDto.email.isEmpty() ||
        !EmailValidator.getInstance().isValid(customerDto.email) ||
        customerDto.email.length > 254) {
        throw GeneralCustomerException("Invalid email")
      }

      if (customerDto.toppings.size > 50) {
        throw GeneralCustomerException("Toppings have exceeded customer limit")
      }

      if (customerDto.toppings.isEmpty()) {
        throw GeneralCustomerException("Must enter at least one topping")
      }
    }
  }
}
