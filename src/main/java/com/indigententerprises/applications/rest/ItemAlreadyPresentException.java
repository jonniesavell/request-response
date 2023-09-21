package com.indigententerprises.applications.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemAlreadyPresentException extends RuntimeException {
}
