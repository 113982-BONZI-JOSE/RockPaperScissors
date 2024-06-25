package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@ControllerAdvice //verifica la ejecución de los controladores
public class    ControllerExceptionHandler {


    /*
    Se utiliza para manejar excepciones especificas en un controlador.
    Permite definir un método que se ejecutara cuando ocurra una excepción particular,
    brindando una respuesta adecuada al cliente.
     */

    /**
     *
     * Tiene que hacer un handler de una excepción, con esto vamos a ver un mensaje de error más elaborado.
     * Toda excepción que no conocemos, que no fue manejada, tiene que caer como un INTERNAL_SERVER_ERROR (codigo 500, eror 500)
     * y nos devuelve el ErrorApi con su mensaje
     */
    @ExceptionHandler(Exception.class) //tiene que hacer un handler de una excepción, con esto vamos a ver un mensaje de error más elaborado
    public ResponseEntity<ErrorApi> handleError(Exception exception){
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    /**
     * Esta ya está manejada, solamente no debería ser devuelta con BAD_REQUEST y pegarno la información del eror en el body
     * que vamos a estar recibiendo de postman
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)//va a validar el MethodArgumentNotValidException
    public ResponseEntity<ErrorApi> handleError(MethodArgumentNotValidException exception){
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorApi> handleError(ResponseStatusException exception){
        ErrorApi error = buildError(exception.getReason(), HttpStatus.valueOf(exception.getStatusCode().value()));
        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorApi> handleError(EntityNotFoundException exception){
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    private  ErrorApi buildError(String message, HttpStatus status){
        return ErrorApi.builder() //patron builder
                .timestamp(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())))
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(message)
                .build();
    }
}
