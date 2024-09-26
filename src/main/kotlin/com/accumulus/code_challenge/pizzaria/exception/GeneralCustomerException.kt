package com.accumulus.code_challenge.pizzaria.exception

class GeneralCustomerException(val exceptionMessage: String = "Customer email already exists") :
  RuntimeException() {}
