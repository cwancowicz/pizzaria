package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.entity.ToppingCount
import com.accumulus.code_challenge.pizzaria.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api")
class ToppingCountController(private val customerService: CustomerService) {

  @GetMapping("/topping-count")
  fun getToppingCounts(@PageableDefault pageable: Pageable): ResponseEntity<Page<ToppingCount>> {
    return ResponseEntity.ok(customerService.getToppingCount(pageable))
  }
}
