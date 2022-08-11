package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "patients")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient extends BaseEntity {

    @Column(name = "patient_last_name", nullable = false)
    String patientLastName;

    @Column(name = "patient_first_name")
    String patientFirstName;

    @Column(name = "patient_midl_name")
    String patientMidlName;

    @Column(name = "date_of_birth")
    String dateOfBirth;

    @Column(name = "phone_number")
    String patientPhoneNumber;

    //@OneToOne
    //@JoinColumn(name = "card_id")
    //Card card;

}
