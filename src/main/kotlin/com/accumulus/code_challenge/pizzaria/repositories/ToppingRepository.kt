package com.accumulus.code_challenge.pizzaria.repositories

import com.accumulus.code_challenge.pizzaria.entity.Topping
import com.accumulus.code_challenge.pizzaria.entity.ToppingCount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : JpaRepository<Topping, Long> {

  @Query(
    "SELECT new com.accumulus.code_challenge.pizzaria.entity.ToppingCount(t.name, COUNT(t) AS numberOfCustomers) FROM Topping t GROUP BY t.name")
  fun getToppingCounts(pageable: Pageable): Page<ToppingCount>
}
