package com.accumulus.code_challenge.pizzaria.mapping

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.entity.Customer

class CustomerMapper {

  companion object {
    fun map(customer: Customer): CustomerDto {
      return CustomerDto(email = customer.email, toppings = ToppingMapper.map(customer.toppings))
    }

    fun map(customerDto: CustomerDto): Customer {
      val customer = Customer(email = customerDto.email)
      customer.toppings = ToppingMapper.map(toppings = customerDto.toppings, customer = customer)
      return customer
    }
  }
}
