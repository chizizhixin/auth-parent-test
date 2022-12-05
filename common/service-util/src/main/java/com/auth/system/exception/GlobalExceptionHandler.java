package com.auth.system.exception;

import com.auth.system.result.Result;
import com.auth.system.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class GlobalExceptionHandler {

	//全局异常处理
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result error(Exception e){
		e.printStackTrace();
		return Result.fail().message("执行了全局异常处理");
	}
	//特定异常处理
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public Result error(ArithmeticException e){
		e.printStackTrace();
		return Result.fail().message("执行了特定异常处理");
	}
	//自定义异常处理
	@ExceptionHandler(AuthException.class)
	@ResponseBody
	public Result error(AuthException e){
		e.printStackTrace();
		return Result.fail().code(e.getCode()).message(e.getMsg());
	}
	/**
	 * spring security异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public Result error(AccessDeniedException e) throws AccessDeniedException {
		return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前功能的权限");
	}
}
