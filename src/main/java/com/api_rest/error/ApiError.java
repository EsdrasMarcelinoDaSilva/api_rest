package com.api_rest.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, String debugMessage){
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message){
        this(status, message, null);
    }
}
