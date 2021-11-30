package com.learn.Exception;

public class InvalidMovieTitle extends Exception{
	
	private String sourceName;
	private String sourceField;
	private Object object;
	public InvalidMovieTitle()
	{
		
	}
	public InvalidMovieTitle(String sourceName, String sourceField, Object object) {
		super(String.format("%s with %s: '%s' is not found", sourceName,sourceField,object));
		this.sourceName = sourceName;
		this.sourceField = sourceField;
		this.object = object;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceField() {
		return sourceField;
	}
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	

}
