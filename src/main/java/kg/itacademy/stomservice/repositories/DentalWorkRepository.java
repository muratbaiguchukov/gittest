package kg.itacademy.stomservice.repositories;

import kg.itacademy.stomservice.entity.DentalWork;
import kg.itacademy.stomservice.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentalWorkRepository extends JpaRepository<DentalWork, Long> {

    List<DentalWork> findAllByDentalWorkName(String dentalWorkName);

    List<DentalWork> findByDentist(Dentist dentist);
}
