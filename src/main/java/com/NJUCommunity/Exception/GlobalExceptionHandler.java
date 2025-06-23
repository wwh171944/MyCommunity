package com.NJUCommunity.Exception;

import com.NJUCommunity.VO.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "请求参数格式错误";
        if (ex.getMessage() != null &&ex.getMessage().contains("not one of the values accepted for Enum class")){
           errorMessage = "参数不在枚举类型中";
        }
        return Response.buildFailure(errorMessage, "400");
    }

    // 新增对MethodArgumentNotValidException的处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Response.buildFailure(errorMessage, "400");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return Response.buildFailure(errorMessage, "400");
    }

    @ExceptionHandler(BindException.class)
    public Response<String> handleBindException(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Response.buildFailure(errorMessage, "400");
    }
}