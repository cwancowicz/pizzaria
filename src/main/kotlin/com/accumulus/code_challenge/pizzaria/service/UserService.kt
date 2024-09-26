package com.accumulus.code_challenge.pizzaria.service

import com.accumulus.code_challenge.pizzaria.domain.ToppingDto
import com.accumulus.code_challenge.pizzaria.domain.UserDto
import com.accumulus.code_challenge.pizzaria.entity.Topping
import com.accumulus.code_challenge.pizzaria.entity.User
import com.accumulus.code_challenge.pizzaria.mapping.ToppingMapper
import com.accumulus.code_challenge.pizzaria.mapping.UserMapper
import com.accumulus.code_challenge.pizzaria.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserService(private val userRepository: UserRepository
)  {

    fun createUser(email: String, toppings: List<ToppingDto>) : UserDto {


        val user = userRepository.save(User(email = email, toppings = ToppingMapper.mapTo(toppings)));
        return UserMapper.map(user)
    }
}