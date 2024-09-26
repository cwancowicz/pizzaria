package com.accumulus.code_challenge.pizzaria.service

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.entity.Customer
import com.accumulus.code_challenge.pizzaria.entity.ToppingCount
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import com.accumulus.code_challenge.pizzaria.mapping.CustomerMapper
import com.accumulus.code_challenge.pizzaria.mapping.ToppingMapper
import com.accumulus.code_challenge.pizzaria.repositories.CustomerRepository
import com.accumulus.code_challenge.pizzaria.repositories.ToppingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerService(
  private val customerRepository: CustomerRepository,
  private val toppingRepository: ToppingRepository
) {

  /**
   * Creates new Customer entity given a CustomerDTO
   *
   * @return new CustomerDto
   */
  fun createCustomerDto(customerDto: CustomerDto): CustomerDto {

    if (customerRepository.existsByEmail(email = customerDto.email)) {
      throw GeneralCustomerException()
    }
    return saveCustomer(customerDto)
  }

  /**
   * Returns the CustomerDto with given email
   *
   * @return CustomerDto if exists, Null if there is no customer with given email
   */
  fun getCustomerDto(email: String): CustomerDto {
    return CustomerMapper.map(getCustomer(email))
  }

  /**
   * Updates a CustomerDto with the given email
   *
   * @return updated CustomerDto
   */
  fun updateCustomerDto(customerDto: CustomerDto): CustomerDto {
    val existingCustomer = getCustomer(customerDto.email)
    existingCustomer.toppings.clear()
    existingCustomer.toppings.addAll(ToppingMapper.map(customerDto.toppings, existingCustomer))

    return CustomerMapper.map(customerRepository.save(existingCustomer))
  }

  /**
   * Returns all toppings with the number of customers who requested them
   *
   * @return ToppingCount
   */
  fun getToppingCount(pageable: Pageable): Page<ToppingCount> {
    return toppingRepository.getToppingCounts(pageable)
  }

  private fun saveCustomer(customerDto: CustomerDto): CustomerDto {
    val customer = CustomerMapper.map(customerDto)
    return CustomerMapper.map(customerRepository.save(customer))
  }

  private fun getCustomer(email: String): Customer {
    return customerRepository.findByEmail(email)
      ?: throw GeneralCustomerException("Customer does not exist")
  }
}
