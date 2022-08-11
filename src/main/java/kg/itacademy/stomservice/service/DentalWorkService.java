package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.models.DentalWorkCreateModel;
import kg.itacademy.stomservice.models.DentalWorkModel;

import java.util.List;

public interface DentalWorkService {

    DentalWorkModel create(DentalWorkCreateModel dentalWorkModel); //создать стом.работы

    boolean update(DentalWorkModel dentalWorkModel); //изменить стом.работы

    boolean deleteById(Long id); //удалить стом.работы по id

    List<DentalWorkModel> getAllByDentalWorkName(String dentalWorkName); //получить все стоматологические работы по наименованию

    List<DentalWorkModel> getAllByDentist(Dentist dentist);// получить все стоматологические работы по стоматологам
}
