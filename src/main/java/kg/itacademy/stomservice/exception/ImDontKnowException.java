package kg.itacademy.stomservice.exception;

import org.springframework.http.HttpStatus;


public class ImDontKnowException extends RuntimeException{
    private HttpStatus httpStatus;

    public ImDontKnowException(String s) {
        super(s);
    }

    public HttpStatus getStatus() {
        return HttpStatus.I_AM_A_TEAPOT;
    }
}