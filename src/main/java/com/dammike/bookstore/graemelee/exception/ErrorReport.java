package com.dammike.bookstore.graemelee.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorReport {
    private Date timestamp;
    private String message;
    private String details;
}
