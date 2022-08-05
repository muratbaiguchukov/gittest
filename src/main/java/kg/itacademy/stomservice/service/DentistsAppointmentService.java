package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.model.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.model.DentistsAppointmentModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DentistsAppointmentService {

    DentistsAppointmentModel create(DentistsAppointmentCreateModel dentistsAppointmentModel); //создать запись к стоматологу

    boolean update(DentistsAppointmentModel dentistsAppointmentModel); //изменить запись к стоматологу

    boolean deleteById(Long id); //удалить запись к стоматологу по id

    List<DentistsAppointmentModel> getAllByVisitDate(LocalDate visitDate); //получить все записи к стоматологу по дате визита

    List<DentistsAppointmentModel> getAllByVisitStartTime(LocalTime visitStartTime); //получить все записи к стоматологу по времени визита

    DentistsAppointmentModel getByPatient(Patient patient); // поиск записи к стоматологу по пациенту

    DentistsAppointmentModel getByDentist(Dentist dentist); //поиск записи к стоматологу по стоматологу

}
