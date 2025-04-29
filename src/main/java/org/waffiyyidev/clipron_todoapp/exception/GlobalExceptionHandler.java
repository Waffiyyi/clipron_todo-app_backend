package org.waffiyyidev.clipron_todoapp.exception;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.waffiyyidev.clipron_todoapp.dtos.ErrorResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e,
                                                                 WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "31",
                                                    e.getHttpStatus());
    return new ResponseEntity<>(errorResponse, e.getHttpStatus());
  }

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<ErrorResponse> handleUnAuthorizedException(
     UnAuthorizedException e, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "33",
                                                    e.getHttpStatus());
    return new ResponseEntity<>(errorResponse, e.getHttpStatus());
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ErrorResponse> handleTokenExpiredException(
     TokenExpiredException e, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "34",
                                                    HttpStatus.IM_USED);
    return new ResponseEntity<>(errorResponse, HttpStatus.IM_USED);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
     ResourceNotFoundException e, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "35",
                                                    e.getHttpStatus());
    return new ResponseEntity<>(errorResponse, e.getHttpStatus());
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e,
                                                                 WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
       "Your session has expired. Please log in again.", "36", HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }





  @ExceptionHandler(CustomAuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(
     CustomAuthenticationException e) {
    ErrorResponse errorResponse = new ErrorResponse(
       "Invalid session or token. Please log in again.", "40", HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }


}
