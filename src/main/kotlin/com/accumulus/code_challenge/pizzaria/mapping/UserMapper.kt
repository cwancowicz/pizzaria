package com.accumulus.code_challenge.pizzaria.mapping

import com.accumulus.code_challenge.pizzaria.domain.UserDto
import com.accumulus.code_challenge.pizzaria.entity.User

class UserMapper {

    companion object {
        fun map(user: User): UserDto {
            return UserDto(email = user.email, toppings = ToppingMapper.mapFrom(user.toppings))
        }
    }
}