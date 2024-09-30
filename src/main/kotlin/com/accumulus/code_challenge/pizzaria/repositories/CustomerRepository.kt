package com.accumulus.code_challenge.pizzaria.repositories

import com.accumulus.code_challenge.pizzaria.entity.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {

  @Nullable fun findByEmail(email: String): Customer?
  fun existsByEmail(email: String): Boolean
  fun findAllByReceivePromos(receivePromos: Boolean, pageable: Pageable): Page<Customer>
  fun findAllByDelivery(delivery: Boolean, pageable: Pageable): Page<Customer>
}
