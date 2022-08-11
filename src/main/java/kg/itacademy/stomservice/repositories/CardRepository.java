package kg.itacademy.stomservice.repositories;

import kg.itacademy.stomservice.entity.Card;
import kg.itacademy.stomservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByNumber(Integer number);

    Card findByPatient(Patient patient);

    List<Card> findAllByPatient(Patient patient);
}
