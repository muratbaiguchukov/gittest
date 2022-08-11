package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.models.DentistCreateModel;
import kg.itacademy.stomservice.models.DentistModel;

import java.util.List;

public interface DentistService {

    DentistModel create(DentistCreateModel dentistModel); //создать стоматолога

    boolean update(DentistModel dentistModel); //изменить стоматолога

    boolean deleteById(Long id); //удалить стоматолога по id

    List<DentistModel> getAllByDentistLastName(String dentistLastName); //получить всех стоматологов по фамилии

    List<DentistModel> getAll();

}
