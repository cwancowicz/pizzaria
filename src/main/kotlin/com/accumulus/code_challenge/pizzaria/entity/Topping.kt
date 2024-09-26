package com.accumulus.code_challenge.pizzaria.entity

import jakarta.persistence.*

@Entity
data class Topping(
  @Id @GeneratedValue val pid: Long? = null,
  @Column(nullable = false) val name: String,
  @ManyToOne(cascade = [CascadeType.MERGE])
  @JoinColumn(name = "customer_id", referencedColumnName = "pid")
  var customer: Customer? = null
)

data class ToppingCount(val name: String, val numberOfCustomers: Long)
