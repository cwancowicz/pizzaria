package com.accumulus.code_challenge.pizzaria.mapping

import com.accumulus.code_challenge.pizzaria.domain.ToppingDto
import com.accumulus.code_challenge.pizzaria.entity.Topping

class ToppingMapper {

    companion object {

        fun mapTo(toppings: List<ToppingDto>) : List<Topping>
        {
            return toppings.map { Topping(name = it.name) }
        }

        fun mapFrom(toppings: List<Topping>) : List<String>
        {
            return toppings.map { it.name }
        }
    }
}