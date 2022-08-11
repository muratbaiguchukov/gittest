package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.exception.*;
import kg.itacademy.stomservice.mappers.DentistsAppointmentMapper;
import kg.itacademy.stomservice.models.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.models.DentistsAppointmentModel;
import kg.itacademy.stomservice.repositories.DentistRepository;
import kg.itacademy.stomservice.repositories.DentistsAppointmentRepository;
import kg.itacademy.stomservice.repositories.PatientRepository;
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
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;

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

        Dentist existDentist = dentistRepository
                .findById(dentistsAppointmentModel.getDentist().getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id " + dentistsAppointmentModel.getDentist().getId()));
        existDentistsAppointment.setDentist(existDentist);

        existDentistsAppointment.setVisitDate(dentistsAppointmentModel.getVisitDate());
        existDentistsAppointment.setVisitStartTime(dentistsAppointmentModel.getVisitStartTime());
        existDentistsAppointment.setVisitEndTime(dentistsAppointmentModel.getVisitEndTime());

        Patient existPatient = patientRepository
                .findById(dentistsAppointmentModel.getPatient().getId())
                .orElseThrow(() -> new PatientNotFoundException("patient not found by id " + dentistsAppointmentModel.getPatient().getId()));
        existDentistsAppointment.setPatient(existPatient);

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
