package kg.itacademy.stomservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class UserModel {
    @NotBlank
    String login;
    @NotBlank
    String password;
    @NotBlank
    @Email
    String email;

}