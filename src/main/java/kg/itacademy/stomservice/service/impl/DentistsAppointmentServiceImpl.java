package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.exceptions.*;
import kg.itacademy.stomservice.mapper.DentistMapper;
import kg.itacademy.stomservice.mapper.DentistsAppointmentMapper;
import kg.itacademy.stomservice.model.AvailableTimeSlotModel;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.model.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.model.DentistsAppointmentModel;
import kg.itacademy.stomservice.repository.DentistsAppointmentRepository;
import kg.itacademy.stomservice.service.DentistsAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DentistsAppointmentServiceImpl implements DentistsAppointmentService {

    private final DentistsAppointmentRepository dentistsAppointmentRepository;

    @Override
    public DentistsAppointmentModel create(DentistsAppointmentCreateModel dentistsAppointmentModel) {
        if (dentistsAppointmentModel == null) {
            throw new DentistsAppointmentModelNullException("Create dentists appointment model is null");
        }

        DentistsAppointment newEntity = DentistsAppointmentMapper.INSTANCE.toEntity(dentistsAppointmentModel);
        newEntity = dentistsAppointmentRepository.save(newEntity);
        return DentistsAppointmentMapper.INSTANCE.toModel(newEntity);
    }

    @Override
    public boolean update(DentistsAppointmentModel dentistsAppointmentModel) {
        if (dentistsAppointmentModel == null) {
            throw new DentistsAppointmentModelNullException("Create dentists appointment model is null");
        }


        DentistsAppointment existDentistsAppointment = dentistsAppointmentRepository
                .findById(dentistsAppointmentModel.getId())
                .orElseThrow(() -> new DentistsAppointmentNotFoundException("dentists appointment not found by id " + dentistsAppointmentModel.getId()));


        existDentistsAppointment.setDentist(dentistsAppointmentModel.getDentist());
        existDentistsAppointment.setVisitDate(dentistsAppointmentModel.getVisitDate());
        existDentistsAppointment.setVisitStartTime(dentistsAppointmentModel.getVisitStartTime());
        existDentistsAppointment.setVisitEndTime(dentistsAppointmentModel.getVisitEndTime());
        existDentistsAppointment.setPatient(dentistsAppointmentModel.getPatient());
        existDentistsAppointment.setRecordStatus(dentistsAppointmentModel.getRecordStatus());
        existDentistsAppointment.setSymptoms(dentistsAppointmentModel.getSymptoms());
        existDentistsAppointment.setDentistsComments(dentistsAppointmentModel.getDentistsComments());

        existDentistsAppointment = dentistsAppointmentRepository.save(existDentistsAppointment);

        return existDentistsAppointment.getId() != null;

    }

    @Override
    public boolean deleteById(Long id) {

        dentistsAppointmentRepository.deleteById(id);

        return true;

    }

    @Override
    public List<DentistsAppointmentModel> getAllByVisitDate(LocalDate visitDate) {

        List<DentistsAppointment> dentistsAppointmentEntityList = dentistsAppointmentRepository.findAllByVisitDate(visitDate);

        List<DentistsAppointmentModel> dentistsAppointmentModelList = DentistsAppointmentMapper.INSTANCE.toListModel(dentistsAppointmentEntityList);


        return dentistsAppointmentModelList;
    }


    @Override
    public List<DentistsAppointmentModel> getAllByVisitStartTime(LocalTime visitStartTime) {

        List<DentistsAppointment> dentistsAppointmentsEntityList = dentistsAppointmentRepository.findAllByVisitStartTime(visitStartTime);

        List<DentistsAppointmentModel> dentistsAppointmentModelList = DentistsAppointmentMapper.INSTANCE.toListModel(dentistsAppointmentsEntityList);

        return dentistsAppointmentModelList;

    }

    @Override
    public DentistsAppointmentModel getByPatient(Patient patient) {

        DentistsAppointment dentistsAppointmentEntity = dentistsAppointmentRepository.findByPatient(patient);

        DentistsAppointmentModel dentistsAppointmentEntityModel = DentistsAppointmentMapper.INSTANCE.toModel(dentistsAppointmentEntity);
        return dentistsAppointmentEntityModel;

    }

    @Override
    public DentistsAppointmentModel getByDentist(Dentist dentist) {

        DentistsAppointment dentistsAppointmentEntity = dentistsAppointmentRepository.findByDentist(dentist);

        DentistsAppointmentModel dentistsAppointmentModel = DentistsAppointmentMapper.INSTANCE.toModel(dentistsAppointmentEntity);

        return dentistsAppointmentModel;


    }

}
