package kg.itacademy.stomservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class DentistCreateModel {

    @NotBlank(message = "dentist last name can't be blank")
    private String dentistLastName;

    private String dentistFirstName;

    private String dentistMidlName;

    private String speciality;

    @NotNull
    private String dentistPhoneNumber;
}
//  String dentistLastName;
//
//   String dentistFirstName;
//
//    @Column(name = "dentist_midl_name")
//    String dentistMidlName;
//
//    @Column(name = "speciality")
//    String speciality;
//
//    @Column(name = "dentist_phone_number")
//    String dentistPhoneNumber;