package com.project.sportyshoes.exception;

public class DuplicateIdException extends Exception {
	
	public DuplicateIdException(String email) {
		super(email+" Already exists");
	}
	
}
