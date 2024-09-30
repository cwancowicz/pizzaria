package com.accumulus.code_challenge.pizzaria.mapping

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.entity.Customer

class CustomerMapper {

  companion object {
    fun map(customer: Customer): CustomerDto {
      return CustomerDto(
        email = customer.email,
        toppings = ToppingMapper.map(customer.toppings),
        receivePromos = customer.receivePromos,
        delivery = customer.delivery)
    }

    fun map(customerDto: CustomerDto): Customer {
      val customer =
        Customer(
          email = customerDto.email,
          receivePromos = customerDto.receivePromos,
          delivery = customerDto.delivery)
      customer.toppings = ToppingMapper.map(toppings = customerDto.toppings, customer = customer)
      return customer
    }
  }
}
