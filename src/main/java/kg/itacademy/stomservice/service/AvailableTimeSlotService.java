package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.models.AvailableTimeSlotCreateModel;
import kg.itacademy.stomservice.models.AvailableTimeSlotModel;

import java.time.LocalDate;
import java.util.List;

public interface AvailableTimeSlotService {

    AvailableTimeSlotModel create(AvailableTimeSlotCreateModel availableTimeSlotModel); //создать доступный временной интервал

    boolean update(AvailableTimeSlotModel availableTimeSlotModel); //изменить доступный временной интервал

    boolean deleteById(Long id); //удалить доступный временной интервал по id

    List<AvailableTimeSlotModel> getAvailableTimeSlotByDentistIdInPeriod(Long dentistId, LocalDate startDate, LocalDate endDate);
}
