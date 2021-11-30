package com.learn.Exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(MovieIdNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleException(MovieIdNotFoundException ex,WebRequest request)
	{
		ExceptionResponse responseEx=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(responseEx,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidMovieTitle.class)
	public ResponseEntity<ExceptionResponse> handleInvalidMovie(InvalidMovieTitle ex,WebRequest request)
	{
		ExceptionResponse res=new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exRes=new ExceptionResponse(new Date(), "Validation Failed", request.getDescription(false));
		Map<String,Object> map=new LinkedHashMap<>();
		map.put("ExeptionDetails",exRes);
		List<String> errors=ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
		map.put("AllFieldErrors", errors);
		return new ResponseEntity<>(map,status);//super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex,WebRequest request)
	{
		List<String> listErr=ex.getConstraintViolations().parallelStream().map(e->e.getMessage()).collect(Collectors.toList());
		ExceptionResponse responseEx=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(responseEx,HttpStatus.BAD_REQUEST);
	}
	
}
