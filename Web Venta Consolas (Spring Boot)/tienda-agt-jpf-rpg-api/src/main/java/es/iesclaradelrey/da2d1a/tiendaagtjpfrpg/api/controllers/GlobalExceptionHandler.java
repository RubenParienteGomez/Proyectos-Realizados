package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.exceptions.ProductoNoEncontradoException;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.exceptions.StockInsuficienteException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ProblemDetail handleNotFound(ProductoNoEncontradoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ProblemDetail handleConflict(StockInsuficienteException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleBadRequest(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}