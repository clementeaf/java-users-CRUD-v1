package users.usersAPI.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class CursoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestHandler(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorHttp message = new ErrorHttp(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                DetalleError.mapearError(ex.getFieldErrors()),
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
