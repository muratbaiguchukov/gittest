package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.*;

import java.time.LocalDate;
import java.util.List;

public interface WorkShiftService {

    WorkShiftModel create(WorkShiftCreateModel workShiftModel); //создать расписание работы стоматолога

    boolean update(WorkShiftModel workShiftModel); //изменить расписание работы стоматолога

    boolean deleteById(Long id); //удалить расписание работы стоматолога по id

    List<WorkShiftModel> getAllByDentist(Dentist dentist); //получить все расписания работы стоматологов по стоматологам

    List<WorkShiftModel> getAllByWorkShiftDay(LocalDate workShiftDay); //получить все расписания работы стоматологов по дням
}
