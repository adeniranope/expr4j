package com.ericsson.expr4j.core.exceptions;

public final class Expression4JException extends IllegalArgumentException {

	private static final long serialVersionUID = -3490136531821156912L;

	private ErrorCode errorCode;
	private String message;
	
	public Expression4JException(String exceptionMessage){
		this.message = exceptionMessage;
	}

	public Expression4JException(ErrorCode errorCode){
		this.errorCode = errorCode;
		this.message = super.getMessage();
	}	
	
	public Expression4JException(ErrorCode errorCode,String exceptionMessage){
		this.errorCode = errorCode;
		this.message = exceptionMessage;
	}
	
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
}
