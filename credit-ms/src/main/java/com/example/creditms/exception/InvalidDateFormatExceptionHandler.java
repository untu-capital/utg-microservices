package com.example.creditms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//A custom exception handler for invalid date formats
public class InvalidDateFormatExceptionHandler extends RuntimeException{
    private String message;
}
