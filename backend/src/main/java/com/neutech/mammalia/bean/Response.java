package com.neutech.mammalia.bean;

import lombok.Data;

@Data
public class Response {
    public Response() {
        this.data = "";
    }

    private Integer code;
    private String message;
    private Object data;
}
