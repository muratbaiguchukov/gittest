package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;

import java.util.List;

public interface DentistService {

    DentistModel create(DentistCreateModel dentistModel); //создать стоматолога

    boolean update(DentistModel dentistModel); //изменить стоматолога

    boolean deleteById(Long id); //удалить стоматолога по id

    List<DentistModel> getAllByDentistLastName(String dentistLastName); //получить всех стоматологов по фамилии

    //List<DentistModel> getAll(); // получить всех дантистов
}
