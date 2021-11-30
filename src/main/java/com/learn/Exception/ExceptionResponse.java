package com.learn.Exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private Object details;
	public ExceptionResponse(Date timestamp, String message, Object details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public ExceptionResponse()
	{
		
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
