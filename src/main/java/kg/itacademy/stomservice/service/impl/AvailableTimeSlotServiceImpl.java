package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.AvailableTimeSlot;
import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.WorkShift;
import kg.itacademy.stomservice.exception.AvailableTimeSlotModelNullException;
import kg.itacademy.stomservice.exception.AvailableTimeSlotNotFoundException;
import kg.itacademy.stomservice.exception.DentistNotFoundException;
import kg.itacademy.stomservice.mappers.AvailableTimeSlotMapper;
import kg.itacademy.stomservice.models.AvailableTimeSlotCreateModel;
import kg.itacademy.stomservice.models.AvailableTimeSlotModel;
import kg.itacademy.stomservice.repositories.AvailableTimeSlotRepository;
import kg.itacademy.stomservice.repositories.DentistRepository;
import kg.itacademy.stomservice.repositories.DentistsAppointmentRepository;
import kg.itacademy.stomservice.repositories.WorkShiftRepository;
import kg.itacademy.stomservice.service.AvailableTimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableTimeSlotServiceImpl implements AvailableTimeSlotService {

    private final AvailableTimeSlotRepository availableTimeSlotRepository;

    private final DentistRepository dentistRepository;

    private final WorkShiftRepository workShiftRepository;

    private final DentistsAppointmentRepository dentistsAppointmentRepository;

    @Override
    public AvailableTimeSlotModel create(AvailableTimeSlotCreateModel availableTimeSlotModel) {

        if (availableTimeSlotModel == null) {
            throw new AvailableTimeSlotModelNullException("Create available time slot model is null");
        }

        AvailableTimeSlot newEntity = AvailableTimeSlotMapper.INSTANCE.toEntity(availableTimeSlotModel);
        newEntity.setIsDeleted(false);
        newEntity = availableTimeSlotRepository.save(newEntity);
        return AvailableTimeSlotMapper.INSTANCE.toModel(newEntity);
    }


    @Override
    public boolean update(AvailableTimeSlotModel availableTimeSlotModel) {

        if (availableTimeSlotModel == null) {
            throw new AvailableTimeSlotModelNullException("Create available time slot model is null");
        }

        AvailableTimeSlot existAvailableTimeSlot = availableTimeSlotRepository
                .findById(availableTimeSlotModel.getId())
                .orElseThrow(() -> new AvailableTimeSlotNotFoundException("available time slot not found by id " + availableTimeSlotModel.getId()));

        //find dentist from db by dentistId
        Dentist existDentist = dentistRepository
                .findById(availableTimeSlotModel.getDentist().getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id "
                        + availableTimeSlotModel.getDentist().getId()));

        //set dentist in existAvailableTimeSlot.setDentist();

        existAvailableTimeSlot.setDentist(existDentist);
        existAvailableTimeSlot.setStartTime(availableTimeSlotModel.getStartTime());
        existAvailableTimeSlot.setEndTime(availableTimeSlotModel.getEndTime());
        existAvailableTimeSlot.setDay(availableTimeSlotModel.getDay());
        existAvailableTimeSlot.setIsDeleted(availableTimeSlotModel.getIsDeleted());

        existAvailableTimeSlot = availableTimeSlotRepository.save(existAvailableTimeSlot);

        return existAvailableTimeSlot.getId() != null;

    }

    @Override
    public boolean deleteById(Long id) {
        AvailableTimeSlot slot = availableTimeSlotRepository.findById(id)
                .orElseThrow();
        slot.setIsDeleted(true);
        availableTimeSlotRepository.save(slot);
        return true;
    }

    @Override
    public List<AvailableTimeSlotModel> getAvailableTimeSlotByDentistIdInPeriod(Long dentistId,
                                                                                LocalDate startDate, LocalDate endDate) {
        Dentist dentist = dentistRepository.findById(dentistId)
                .orElseThrow();
        // 3. Получаем расписание выбранного стоматолога
        List<WorkShift> workShifts = workShiftRepository.findByDentistAndWorkShiftDayBetween(dentist, startDate, endDate);
        List<AvailableTimeSlotModel> availableTimeSlotModels = new ArrayList<>();

        //4. Формируем список свободных слотов на основе 1 workshift
        for (WorkShift element : workShifts) {
            List<AvailableTimeSlot> availableTimeSlots = calculateAvailableTimeSlot(element, dentist);
            //convert to model list
            List<AvailableTimeSlotModel> availableTimeSlotModelList = AvailableTimeSlotMapper.INSTANCE.toListModel(availableTimeSlots);
            availableTimeSlotModels.addAll(availableTimeSlotModelList);
        }

        return availableTimeSlotModels;
    }

    public List<AvailableTimeSlot> calculateAvailableTimeSlot(WorkShift workShift, Dentist dentist) {
        List<AvailableTimeSlot> timeSlots = new ArrayList<>();
        LocalTime currentTime = workShift.getWorkShiftStartTime();
        while (currentTime.isBefore(workShift.getWorkShiftEndTime())) {
            LocalTime startTime = currentTime;
            LocalTime endTime = currentTime.plusHours(1);

            DentistsAppointment appointment = dentistsAppointmentRepository
                    .findByDentistAndVisitStartTimeEqualsAndVisitEndTimeEquals(dentist, startTime, endTime);


            Boolean isAvail = appointment == null;
            currentTime = currentTime.plusHours(1);

            if (isAvail) {
                AvailableTimeSlot sls = new AvailableTimeSlot();
                sls.setStartTime(startTime);
                sls.setEndTime(endTime);
                sls.setDay(workShift.getWorkShiftDay());
                sls.setDentist(dentist);
                timeSlots.add(sls);
            }

        }

        return timeSlots;
    }


}
