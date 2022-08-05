package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.model.AvailableTimeSlotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DentistsAppointmentRepository extends JpaRepository<DentistsAppointment, Long> {

    List<DentistsAppointment> findAllByVisitDate(LocalDate visitDate);

    List<DentistsAppointment> findAllByVisitStartTime(LocalTime visitStartTime);

    DentistsAppointment findByPatient(Patient patient);

    DentistsAppointment findByDentist(Dentist dentist);

    DentistsAppointment findByDentistAndVisitStartTimeEqualsAndVisitEndTimeEquals(Dentist dentist, LocalTime startTime, LocalTime endTime);



}
