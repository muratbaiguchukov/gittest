package kg.itacademy.stomservice.repositories;

import kg.itacademy.stomservice.entity.AvailableTimeSlot;
import kg.itacademy.stomservice.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AvailableTimeSlotRepository extends JpaRepository<AvailableTimeSlot, Long> {

    List<AvailableTimeSlot> findAllByDentistAndStartTimeEqualsAndEndTimeEquals(Dentist dentist, LocalTime startTime, LocalTime endTime);

    AvailableTimeSlot findByDentistAndStartTimeEqualsAndEndTimeEquals(Dentist dentist, LocalTime startTime, LocalTime endTime);
}
