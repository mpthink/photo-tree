package com.photoTree.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.photoTree.utils.ResponseUtil;

@ControllerAdvice
@Order
public class GlobalExceptionHandler {

    private Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object badArgumentHandler(IllegalArgumentException e, HttpServletRequest request) {
        logger.error(request.getRequestURL() +"异常：" + e.getMessage());
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object badArgumentHandler(MethodArgumentTypeMismatchException e,HttpServletRequest request) {
        logger.error(request.getRequestURL() +"异常：" + e.getMessage());
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object badArgumentHandler(MissingServletRequestParameterException e,HttpServletRequest request) {
        logger.error(request.getRequestURL() +"异常：" + e.getMessage());
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Object badArgumentHandler(HttpMessageNotReadableException e,HttpServletRequest request) {
        logger.error(request.getRequestURL() +"异常：" + e.getMessage());
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handlerException(MethodArgumentNotValidException e,HttpServletRequest request){
        logger.error(request.getRequestURL() + "异常: "+ e.getMessage());
        return ResponseUtil.fail(402,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object seriousHandler(Exception e) {
        logger.error(e.getMessage(), e);
	        return ResponseUtil.serious();
    }



}
