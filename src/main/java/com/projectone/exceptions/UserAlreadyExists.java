package com.projectone.exceptions;

public class UserAlreadyExists extends Exception{
  public UserAlreadyExists(String message) {
    super(message);
  }
}
