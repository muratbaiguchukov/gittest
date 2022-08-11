package kg.itacademy.stomservice.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorModel {
    private String msg;
    private String exceptionClassName;
}