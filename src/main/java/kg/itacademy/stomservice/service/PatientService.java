package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.models.PatientCreateModel;
import kg.itacademy.stomservice.models.PatientModel;

import java.util.List;

public interface PatientService {
    PatientModel create(PatientCreateModel patientModel); //создать пациента

    boolean update(PatientModel patientModel); //изменить пациента

    boolean deleteById(Long id); //удалить пациента по id

    List<PatientModel> getAllByPatientLastName(String patientLastName); //получить всех пациентов по фамилии
}
