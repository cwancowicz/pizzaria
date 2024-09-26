package com.accumulus.code_challenge.pizzaria.controller

import com.accumulus.code_challenge.pizzaria.exception.ControllerExceptionHandler
import com.accumulus.code_challenge.pizzaria.exception.GeneralCustomerException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ControllerExceptionHandlerTest {

  private val controllerExceptionHandler: ControllerExceptionHandler = ControllerExceptionHandler()
  @Test
  fun shouldReturnBadRequestWithMessage() {

    val message = "Test Exception message"
    val exception = GeneralCustomerException(message)
    val responseEntity = controllerExceptionHandler.handleGeneralCustomerException(exception)

    assertEquals(422, responseEntity.body!!.errorCode)
    assertEquals(message, responseEntity.body!!.message)
  }
}
