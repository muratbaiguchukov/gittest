package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

        List<WorkShift> findAllByDentist(Dentist dentist);

        List<WorkShift> findAllByWorkShiftDay(LocalDate workShiftDay);

    @Query("select w from WorkShift w")
    List<WorkShift> findByDentistAndWorkShiftDayBetween();

        WorkShift findByDentist(Dentist dentist);


    List<WorkShift> findByDentistAndWorkShiftDayBetween(Dentist dentist, LocalDate startDay, LocalDate endDay);
}
