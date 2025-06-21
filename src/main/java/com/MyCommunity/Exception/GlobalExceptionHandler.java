package com.MyCommunity.Exception;

import com.MyCommunity.VO.Response;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Response.buildFailure(errorMessage, "400");
    }

    @ExceptionHandler(value = CommunityException.class)
    public Response<String> handleAIExternalException(CommunityException e) {
        e.printStackTrace();
        if (e.getMessage().equals("未登录，请先登录")) {
            return Response.buildFailure(e.getMessage(), "401");
        }
        return Response.buildFailure(e.getMessage(), "400");
    }
}
