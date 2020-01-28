package com.chaapu.springstarter.pizzaorder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "PizzaOrder not found!")
public class PizzaOrderNotFoundException extends  RuntimeException{

}
