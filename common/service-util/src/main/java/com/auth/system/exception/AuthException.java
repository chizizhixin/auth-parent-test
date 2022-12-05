package com.auth.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthException extends RuntimeException{

	private Integer code;

	private String msg;

}
