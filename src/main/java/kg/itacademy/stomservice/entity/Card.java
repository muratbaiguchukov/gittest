package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity {

    @Column(name = "number")
    Integer number;

    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "dental_work_id")
    DentalWork dentalWork;

    @Column(name = "patient_status")
    @Enumerated(value = EnumType.STRING)
    PatientStatus patientStatus;

    @Column(name = "debt")
    BigDecimal debt;

}
