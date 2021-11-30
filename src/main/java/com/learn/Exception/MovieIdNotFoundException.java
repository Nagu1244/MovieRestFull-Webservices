package com.learn.Exception;

public class MovieIdNotFoundException extends Exception {
	
	private String source;
	private String sourceField;
	private Object sourceFieldValue;
	
	public MovieIdNotFoundException()
	{
		
	}
	public MovieIdNotFoundException(String source, String sourceField, Object sourceFieldValue) {
		super(String.format("%s with %s:'%s' is not found", source,sourceField,sourceFieldValue));
		this.source = source;
		this.sourceField = sourceField;
		this.sourceFieldValue = sourceFieldValue;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceField() {
		return sourceField;
	}
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	public Object getSourceFieldValue() {
		return sourceFieldValue;
	}
	public void setSourceFieldValue(Object sourceFieldValue) {
		this.sourceFieldValue = sourceFieldValue;
	}
	
	

}
