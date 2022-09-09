package com.projectone.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAllowed extends RuntimeException{
  public NotAllowed(String message) {
    super(message);
  }
}