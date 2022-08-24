package com.projectone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NoSuchUser extends Exception{
  public NoSuchUser(String message) {
    super(message);
  }
}
