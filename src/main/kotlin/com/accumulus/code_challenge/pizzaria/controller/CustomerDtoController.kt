package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.service.CustomerService
import com.accumulus.code_challenge.pizzaria.validator.CustomerDtoValidator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api")
class CustomerDtoController(private val customerService: CustomerService) {

  @PostMapping("/customer")
  fun createCustomerDto(@RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> {
    CustomerDtoValidator.validate(customerDto)
    return ResponseEntity.ok(customerService.createCustomerDto(customerDto))
  }

  @PutMapping("/customer")
  fun updateCustomerDto(@RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> {
    CustomerDtoValidator.validate(customerDto)
    return ResponseEntity.ok(customerService.updateCustomerDto(customerDto))
  }

  @GetMapping("/customer/{email}")
  fun getCustomerDto(@PathVariable(value = "email") email: String): ResponseEntity<CustomerDto> {
    return ResponseEntity.ok(customerService.getCustomerDto(email))
  }
}
