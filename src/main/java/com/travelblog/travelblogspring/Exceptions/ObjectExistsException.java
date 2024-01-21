package com.travelblog.travelblogspring.Exceptions;

@SuppressWarnings("serial")
public class ObjectExistsException 
	extends RuntimeException {
	  public ObjectExistsException(String errorMessage) {
	      super(errorMessage);
	  }
}
