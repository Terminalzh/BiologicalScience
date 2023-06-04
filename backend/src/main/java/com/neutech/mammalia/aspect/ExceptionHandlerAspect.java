package com.neutech.mammalia.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAspect {
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, Object> handleDuplicateKeyException(SQLIntegrityConstraintViolationException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.CONFLICT.value());
        map.put("message", e.getMessage());
        return map;
    }
}
