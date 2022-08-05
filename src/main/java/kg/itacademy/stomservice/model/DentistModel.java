package kg.itacademy.stomservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class DentistModel {

    @NotNull
    private Long id;

    @NotBlank(message = "dentist last name can't be blank")
    private String dentistLastName;

    private String dentistFirstName;

    private String dentistMidlName;

    private String speciality;

    @NotNull
    private String dentistPhoneNumber;
}
