package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.domain.CustomerDto
import com.accumulus.code_challenge.pizzaria.service.CustomerService
import com.accumulus.code_challenge.pizzaria.validator.CustomerDtoValidator
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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

  @GetMapping("/customer")
  fun getCustomerDtos(
    @RequestParam("qf", required = false, defaultValue = "") queryField: String,
    @RequestParam("qv", required = false) queryValue: Boolean,
    @PageableDefault pageable: Pageable
  ): ResponseEntity<Page<CustomerDto>> {
    return ResponseEntity.ok(
      customerService.getCustomerDtos(pageable = pageable, qf = queryField, qv = queryValue))
  }
}
