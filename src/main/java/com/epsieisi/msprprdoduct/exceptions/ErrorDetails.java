package com.epsieisi.msprprdoduct.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private int statusCode;
    private String message;
    private String details;
}
