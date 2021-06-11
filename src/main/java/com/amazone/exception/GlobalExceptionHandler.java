package com.amazone.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazone.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Media type not Supported");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Input Mismatch");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	@ExceptionHandler(NotSufficientBalanceException.class)
	protected ResponseEntity<Object> handleStudentNotFoundException(NotSufficientBalanceException ex) {
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String reason = HttpStatus.BAD_REQUEST.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Book Not Found For this ID");
		ApiErrors  errors = new ApiErrors(LocalDateTime.now(),status.value(),reason,message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(errors);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	protected ResponseEntity<Object> handleIDNotFoundException(IdNotFoundException ex) {
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "ID Not Found");
		ApiErrors  errors = new ApiErrors(LocalDateTime.now(),status.value(),reason,message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	protected ResponseEntity<Object> handleCityNotFoundException(CategoryNotFoundException ex) {
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Category Not Found");
		ApiErrors  errors = new ApiErrors(LocalDateTime.now(),status.value(),reason,message);
		return ResponseEntity.status(status).headers(header).body(errors);
	}
	
}
