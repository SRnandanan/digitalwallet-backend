package com.os.digitalwallet.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {

    private String message;
    private HttpStatus statusCode;
}
