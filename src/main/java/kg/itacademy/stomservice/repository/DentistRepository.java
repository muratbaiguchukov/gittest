package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.DentistModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentistRepository extends JpaRepository<Dentist, Long> {

    List<Dentist> findAllByDentistLastName(String dentistLastName);

    Dentist findByDentistLastName(String dentistLastName);

    //List<Dentist> getAll();
}
