package com.accumulus.code_challenge.pizzaria.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

  @ExceptionHandler(value = [GeneralCustomerException::class])
  fun handleGeneralCustomerException(
    generalCustomerException: GeneralCustomerException
  ): ResponseEntity<BadRequestBody> {

    return ResponseEntity.badRequest()
      .body(BadRequestBody(message = generalCustomerException.exceptionMessage, errorCode = 422))
  }
}
