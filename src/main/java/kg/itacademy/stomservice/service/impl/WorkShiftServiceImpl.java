package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.WorkShift;
import kg.itacademy.stomservice.exception.DentistNotFoundException;
import kg.itacademy.stomservice.exception.WorkShiftModelNullException;
import kg.itacademy.stomservice.exception.WorkShiftNotFoundException;
import kg.itacademy.stomservice.mappers.WorkShiftMapper;
import kg.itacademy.stomservice.models.WorkShiftCreateModel;
import kg.itacademy.stomservice.models.WorkShiftModel;
import kg.itacademy.stomservice.repositories.DentistRepository;
import kg.itacademy.stomservice.repositories.WorkShiftRepository;
import kg.itacademy.stomservice.service.WorkShiftService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkShiftServiceImpl implements WorkShiftService {

    private final WorkShiftRepository workShiftRepository;
    private final DentistRepository dentistRepository;

    @Override
    public WorkShiftModel create(WorkShiftCreateModel workShiftModel) {
        if (workShiftModel == null) {
            throw new WorkShiftModelNullException("Create work shift model is null");
        }

        WorkShift newEntity = WorkShiftMapper.INSTANCE.toEntity(workShiftModel);
        newEntity = workShiftRepository.save(newEntity);
        return WorkShiftMapper.INSTANCE.toModel(newEntity);
    }

    @Override
    public boolean update(WorkShiftModel workShiftModel) {
        if (workShiftModel == null) {
            throw new WorkShiftModelNullException("Create work shift model is null");
        }


        WorkShift existWorkShift = workShiftRepository
                .findById(workShiftModel.getId())
                .orElseThrow(() -> new WorkShiftNotFoundException("work shift not found by id " + workShiftModel.getId()));

        Dentist existDentist = dentistRepository
                .findById(workShiftModel.getDentist().getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id " + workShiftModel.getDentist().getId()));
        existWorkShift.setDentist(existDentist);
        existWorkShift.setStartDate(workShiftModel.getStarDate());
        existWorkShift.setEndDate(workShiftModel.getEndDate());
        existWorkShift.setWorkShiftStartTime(workShiftModel.getWorkShiftStartTime());
        existWorkShift.setWorkShiftEndTime(workShiftModel.getWorkShiftEndTime());
        existWorkShift.setWorkShiftDay(workShiftModel.getWorkShiftDay());

        existWorkShift = workShiftRepository.save(existWorkShift);

        return existWorkShift.getId() != null;

    }

    @Override
    public boolean deleteById(Long id) {

        workShiftRepository.deleteById(id);

        return true;
    }

    @Override
    public List<WorkShiftModel> getAllByDentist(Long dentistId) {
        Dentist dentist = dentistRepository.findById(dentistId).orElseThrow();
        List<WorkShift> workShiftEntityList = workShiftRepository.findAllByDentist(dentist);
        List<WorkShiftModel> workShiftModelList = WorkShiftMapper.INSTANCE.toListModel(workShiftEntityList);


        return workShiftModelList;
    }

    
    @Override
    public List<WorkShiftModel> getAllByWorkShiftDay(LocalDate workShiftDay) {
        List<WorkShift> workShiftEntityList = workShiftRepository.findAllByWorkShiftDay(workShiftDay);

        List<WorkShiftModel> workShiftModelList = WorkShiftMapper.INSTANCE.toListModel(workShiftEntityList);


        return workShiftModelList;
    }
}
