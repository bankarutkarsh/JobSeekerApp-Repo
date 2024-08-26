package com.job.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.job.beans.ApiResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ApiResponse> handleJobNotFoundException(JobNotFoundException exception){
		ApiResponse response = new ApiResponse(exception.getMessage(),404);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException exception){
		ApiResponse response = new ApiResponse(exception.getMessage(),404);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errors = exception.getBindingResult().getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		errors.forEach(err->{
			String key = ((FieldError)err).getField();
			String value = err.getDefaultMessage();
			map.put(key, value);
		});
		ApiResponse response = new ApiResponse(map.toString(),404);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	
	
}
