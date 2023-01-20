package com.jbv.lebenslauftool.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicantNotFoundException extends RuntimeException {
    public ApplicantNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
