package urfu.example.yandexdirect.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({RuntimeException.class, Exception.class})
  public final ResponseEntity handleRuntimeException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        exception.getMessage() == null ? "Неизвестная ошибка" : exception.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
