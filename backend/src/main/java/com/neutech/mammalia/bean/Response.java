package com.neutech.mammalia.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {

    private Integer code;
    private String message;
    private Object data;

    public Response() {
        this.message = "";
        this.data = "";
    }

    public Response(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = httpStatus.getReasonPhrase();
    }

    public Response(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
        this.data = httpStatus.getReasonPhrase();
    }

    public Response(HttpStatus httpStatus, Object data) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public Response(HttpStatus httpStatus, String message, Object data) {
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
    }

}
