package com.neutech.mammalia.aspect;

import com.neutech.mammalia.bean.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAspect {
    @ResponseBody
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, LoginException.class})
    public ResponseEntity<Response> handleDuplicateKeyException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response(HttpStatus.CONFLICT, e.getMessage()));
    }
}
