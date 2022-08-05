package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.AvailableTimeSlot;
import kg.itacademy.stomservice.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface AvailableTimeSlotRepository extends JpaRepository<AvailableTimeSlot, Long> {

    List<AvailableTimeSlot> findAllByDentistAndStartTimeEqualsAndEndTimeEquals(Dentist dentist, LocalTime startTime, LocalTime endTime);

    AvailableTimeSlot findByDentistAndStartTimeEqualsAndEndTimeEquals(Dentist dentist, LocalTime startTime, LocalTime endTime);
}
