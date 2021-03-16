package com.example.demo.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * GenericResponse is a response from BeverageBuilder
 */
@Getter
@Setter
@AllArgsConstructor
public class GenericResponse implements Serializable {

    private Boolean status;
    private String message;
    private Exception exception;
}
