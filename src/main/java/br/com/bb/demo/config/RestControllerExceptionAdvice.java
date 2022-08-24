package br.com.bb.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessage> errorMessages = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    return new ErrorMessage(((FieldError) error).getField(), error.getDefaultMessage());
                })
                .toList();

        return ResponseEntity.badRequest().body(errorMessages);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class ErrorMessage {
        private String field;
        private String message;
    }
}
