package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.exceptions.DentistModelNullException;
import kg.itacademy.stomservice.exceptions.DentistNotFoundException;
import kg.itacademy.stomservice.mapper.DentistMapper;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.repository.DentistRepository;
import kg.itacademy.stomservice.service.DentistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    @Override
    public DentistModel create(DentistCreateModel dentistModel) {

        if (dentistModel == null) {
            throw new DentistModelNullException("Create dentist model is null");
        }

        Dentist newEntity = DentistMapper.INSTANCE.toEntity(dentistModel);
        newEntity = dentistRepository.save(newEntity);
        return DentistMapper.INSTANCE.toModel(newEntity);

    }

    @Override
    public boolean update(DentistModel dentistModel) {
        if (dentistModel == null) {
            throw new DentistModelNullException("Create dentist model is null");
        }


        Dentist existDentist = dentistRepository
                .findById(dentistModel.getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id " + dentistModel.getId()));


        existDentist.setDentistLastName(dentistModel.getDentistLastName());
        existDentist.setDentistFirstName(dentistModel.getDentistFirstName());
        existDentist.setDentistMidlName(dentistModel.getDentistMidlName());
        existDentist.setDentistPhoneNumber(dentistModel.getDentistPhoneNumber());
        existDentist.setSpeciality(dentistModel.getSpeciality());

        existDentist = dentistRepository.save(existDentist);

        return existDentist.getId() != null;


    }

    @Override
    public boolean deleteById(Long id) {

        dentistRepository.deleteById(id);

        return true;

    }

    @Override
    public List<DentistModel> getAllByDentistLastName(String dentistLastName) {
        List<Dentist> dentistEntityList = dentistRepository.findAllByDentistLastName(dentistLastName);

        List<DentistModel> dentistModelList = DentistMapper.INSTANCE.toListModel(dentistEntityList);


        return dentistModelList;
    }

//    @Override
//    public List<DentistModel> getAll() {
//        List<Dentist> dentistEntityList = dentistRepository.getAll();
//        List<DentistModel> dentistModelList = DentistMapper.INSTANCE.toListModel(dentistEntityList);
//        return dentistModelList;
//    }


}

