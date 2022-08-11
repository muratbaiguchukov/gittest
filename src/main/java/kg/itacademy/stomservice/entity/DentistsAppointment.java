package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "dentists_appointment",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"visit_start_time", "visit_end_time", "visit_date"})
})
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DentistsAppointment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist dentist;

    @Column(name = "visit_date")
    LocalDate visitDate;

    @Column(name = "visit_start_time")
    LocalTime visitStartTime;

    @Column(name = "visit_end_time")
    LocalTime visitEndTime;

    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @Column(name = "record_status")
    @Enumerated(value = EnumType.STRING)
    RecordStatus recordStatus;

    @Column(name = "symptoms")
    String symptoms;

    @Column(name = "dentists_comments")
    String dentistsComments;


}
