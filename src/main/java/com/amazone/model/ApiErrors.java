package com.amazone.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	
}
