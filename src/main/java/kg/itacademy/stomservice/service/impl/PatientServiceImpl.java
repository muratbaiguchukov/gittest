package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.exceptions.DentistModelNullException;
import kg.itacademy.stomservice.exceptions.DentistNotFoundException;
import kg.itacademy.stomservice.exceptions.PatientModelNullException;
import kg.itacademy.stomservice.exceptions.PatientNotFoundException;
import kg.itacademy.stomservice.mapper.DentistMapper;
import kg.itacademy.stomservice.mapper.PatientMapper;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.model.PatientCreateModel;
import kg.itacademy.stomservice.model.PatientModel;
import kg.itacademy.stomservice.repository.PatientRepository;
import kg.itacademy.stomservice.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Override
    public PatientModel create(PatientCreateModel patientModel) {
        if (patientModel == null) {
            throw new PatientModelNullException("Create patient model is null");
        }

        Patient newEntity = PatientMapper.INSTANCE.toEntity(patientModel);
        newEntity = patientRepository.save(newEntity);
        return PatientMapper.INSTANCE.toModel(newEntity);
    }

    @Override
    public boolean update(PatientModel patientModel) {
        if (patientModel == null) {
            throw new PatientModelNullException("Create patient model is null");
        }


        Patient existPatient = patientRepository
                .findById(patientModel.getId())
                .orElseThrow(() -> new PatientNotFoundException("patient not found by id " + patientModel.getId()));


        existPatient.setPatientLastName(patientModel.getPatientLastName());
        existPatient.setPatientFirstName(patientModel.getPatientFirstName());
        existPatient.setPatientMidlName(patientModel.getPatientMidlName());
        existPatient.setDateOfBirth(patientModel.getDateOfBirth());
        existPatient.setPatientPhoneNumber(patientModel.getPatientPhoneNumber());


        existPatient = patientRepository.save(existPatient);

        return existPatient.getId() != null;

    }

    @Override
    public boolean deleteById(Long id) {

        patientRepository.deleteById(id);

        return true;

    }

    @Override
    public List<PatientModel> getAllByPatientLastName(String patientLastName) {

            List<Patient> patientEntityList = patientRepository.findAllByPatientLastName(patientLastName);

            List<PatientModel> patientModelList = PatientMapper.INSTANCE.toListModel(patientEntityList);

            return patientModelList;
        }
    }
