package com.project.sportyshoes.exception;

public class DataNotFoundException extends Exception{
	public DataNotFoundException(){
		super("Requested data not found");
	}
}
