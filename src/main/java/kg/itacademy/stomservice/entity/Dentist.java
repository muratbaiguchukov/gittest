package kg.itacademy.stomservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "dentists")
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Dentist extends BaseEntity {
    @Column(name = "dentist_last_name", nullable = false)
    String dentistLastName;

    @Column(name = "dentist_first_name")
    String dentistFirstName;

    @Column(name = "dentist_midl_name")
    String dentistMidlName;

    @Column(name = "speciality")
    String speciality;

    @Column(name = "dentist_phone_number")
    String dentistPhoneNumber;

    @Column(name = "email")
    String email;
}