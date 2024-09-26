package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.domain.ToppingDto
import com.accumulus.code_challenge.pizzaria.domain.UserDto
import com.accumulus.code_challenge.pizzaria.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api")
class UserController(private val userService: UserService){

    @PostMapping("/user")
    fun createUser(email: String, toppings: List<ToppingDto>) : UserDto {
        return userService.createUser(email, toppings);
    }
}