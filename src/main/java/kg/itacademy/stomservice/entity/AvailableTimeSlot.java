package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "available_time_slot",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"start_time", "end_time", "slot_day"})
        })
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableTimeSlot extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist dentist;

    @Column(name = "start_time", unique = true)
    LocalTime startTime;

    @Column(name = "end_time", unique = true)
    LocalTime endTime;

    @Column(name = "slot_day")
    LocalDate day;

    @Column(name = "is_active")
    Boolean isDeleted = Boolean.FALSE;

}
