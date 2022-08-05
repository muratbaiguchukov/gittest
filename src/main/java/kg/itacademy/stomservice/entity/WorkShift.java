package kg.itacademy.stomservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "dentists_schedule")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkShift extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    Dentist dentist;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "work_shift_start_time")
    LocalTime workShiftStartTime;

    @Column(name = "work_shift_end_time")
    LocalTime workShiftEndTime;

    @Column(name = "work_shift_day")
    LocalDate workShiftDay;

}
