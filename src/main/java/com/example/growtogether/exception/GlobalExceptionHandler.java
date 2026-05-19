package com.example.growtogether.exception;

import com.example.growtogether.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<ErrorResponse> passwordNotMatchedException(PasswordNotMatchedException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> refreshTokenExpiredException(RefreshTokenExpiredException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(RefreshTokenNotValidException.class)
    public ResponseEntity<ErrorResponse> refreshTokenNotValidException(RefreshTokenNotValidException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidFoodTypeException.class)
    public ResponseEntity<ErrorResponse> invalidFoodTypeException(InvalidFoodTypeException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInvitationStatusException.class)
    public ResponseEntity<ErrorResponse> invalidInvitationStatusException(InvalidInvitationStatusException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ErrorResponse> invalidOrderStatusException(InvalidOrderStatusException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidRestaurantTypeException.class)
    public ResponseEntity<ErrorResponse> invalidRestaurantTypeException(InvalidRestaurantTypeException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponse> invalidRoleException(InvalidRoleException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTableSessionStatusException.class)
    public ResponseEntity<ErrorResponse> invalidTableSessionStatusException(InvalidTableSessionStatusException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorResponse> unauthorizedException(HttpClientErrorException.Unauthorized ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ErrorResponse> forbiddenException(HttpClientErrorException.Forbidden ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(EmailSendingFailureException.class)
    public ResponseEntity<ErrorResponse> EmailSendingFailureException(EmailSendingFailureException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(InvitationStillValidException.class)
    public ResponseEntity<ErrorResponse> invitationStillValidException(InvitationStillValidException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvitationExpiredException.class)
    public ResponseEntity<ErrorResponse> invitationExpiredException(InvitationExpiredException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> restaurantNotFoundException(RestaurantNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RestaurantBranchesExistsException.class)
    public ResponseEntity<ErrorResponse> restaurantBranchesExistsException(RestaurantBranchesExistsException ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
