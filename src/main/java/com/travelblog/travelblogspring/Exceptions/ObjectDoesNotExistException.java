package com.travelblog.travelblogspring.Exceptions;

@SuppressWarnings("serial")
public class ObjectDoesNotExistException 
	extends RuntimeException {
	  public ObjectDoesNotExistException(String errorMessage) {
	      super(errorMessage);
	  }
}
