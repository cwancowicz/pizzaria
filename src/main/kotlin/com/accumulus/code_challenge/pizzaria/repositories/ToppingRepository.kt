package com.accumulus.code_challenge.pizzaria.repositories

import com.accumulus.code_challenge.pizzaria.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ToppingRepository : JpaRepository<User, Long>{
}