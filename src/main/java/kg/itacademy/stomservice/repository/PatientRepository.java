package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByPatientLastName(String patientLastName);

    Patient findByPatientLastName(String patientLastName);
}
