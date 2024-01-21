package com.travelblog.travelblogspring.Exceptions;

@SuppressWarnings("serial")
public class UserDoesNotExistException 
	extends RuntimeException {
	  public UserDoesNotExistException(String errorMessage) {
	      super(errorMessage);
	  }
}
