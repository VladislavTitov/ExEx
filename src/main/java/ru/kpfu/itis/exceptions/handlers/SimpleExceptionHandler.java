package ru.kpfu.itis.exceptions.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.exceptions.shared.InvalidCredentialsException;
import ru.kpfu.itis.exceptions.shared.NoContentException;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;

/**
 * 600 No Content: со стороны клиента пришли неполные данные
 * 601 Invalid Credentials: неверный логин или пароль
 * 602 No Such Id: не существует записи в бд с таким ид
 */
@ControllerAdvice
public class SimpleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoContentException.class)
    protected ResponseEntity<Object> handleNoContentException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Response<Object>(600, null, ex.getMessage()),
                new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    protected ResponseEntity<Object> handleInvalidCredentialException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Response<Object>(601, null, ex.getMessage()),
                new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler(value = NoSuchIdException.class)
    protected ResponseEntity<Object> handleNoSuchIdException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Response<Object>(602, null, ex.getMessage()),
                new HttpHeaders(), HttpStatus.OK, request);
    }

}
