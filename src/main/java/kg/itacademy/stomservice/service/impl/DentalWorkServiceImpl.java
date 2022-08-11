package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.DentalWork;
import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.exception.DentalWorkModelNullException;
import kg.itacademy.stomservice.exception.DentalWorkNotFoundException;
import kg.itacademy.stomservice.exception.DentistNotFoundException;
import kg.itacademy.stomservice.mappers.DentalWorkMapper;
import kg.itacademy.stomservice.models.DentalWorkCreateModel;
import kg.itacademy.stomservice.models.DentalWorkModel;
import kg.itacademy.stomservice.repositories.DentalWorkRepository;
import kg.itacademy.stomservice.repositories.DentistRepository;
import kg.itacademy.stomservice.service.DentalWorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DentalWorkServiceImpl implements DentalWorkService {

    private final DentalWorkRepository dentalWorkRepository;
    private final DentistRepository dentistRepository;

    @Override
    public DentalWorkModel create(DentalWorkCreateModel dentalWorkModel) {
        if (dentalWorkModel == null) {
            throw new DentalWorkModelNullException("Create dental works model is null");
        }

        DentalWork newEntity = DentalWorkMapper.INSTANCE.toEntity(dentalWorkModel);
        newEntity = dentalWorkRepository.save(newEntity);
        return DentalWorkMapper.INSTANCE.toModel(newEntity);

    }

    @Override
    public boolean update(DentalWorkModel dentalWorkModel) {
        if (dentalWorkModel == null) {
            throw new DentalWorkModelNullException("Create dental works model is null");
        }


        DentalWork existDentalWork = dentalWorkRepository
                .findById(dentalWorkModel.getId())
                .orElseThrow(() -> new DentalWorkNotFoundException("dental work not found by id " + dentalWorkModel.getId()));


        existDentalWork.setDentalWorkName(dentalWorkModel.getDentalWorkName());
        existDentalWork.setCode(dentalWorkModel.getCode());
        existDentalWork.setCategory(dentalWorkModel.getCategory());

        Dentist existDentist = dentistRepository
                .findById(dentalWorkModel.getDentist().getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id " + dentalWorkModel.getDentist().getId()));
        existDentalWork.setDentist(existDentist);

        existDentalWork.setPrice(dentalWorkModel.getPrice());


        existDentalWork = dentalWorkRepository.save(existDentalWork);

        return existDentalWork.getId() != null;

    }

    @Override
    public boolean deleteById(Long id) {
        dentalWorkRepository.deleteById(id);

        return true;
    }

    @Override
    public List<DentalWorkModel> getAllByDentalWorkName(String dentalWorkName) {
        List<DentalWork> dentalWorksEntityList = dentalWorkRepository.findAllByDentalWorkName(dentalWorkName);

        List<DentalWorkModel> dentalWorkModelList = DentalWorkMapper.INSTANCE.toListModel(dentalWorksEntityList);

        return dentalWorkModelList;
    }

    @Override
    public List<DentalWorkModel> getAllByDentist(Dentist dentist) {

        List<DentalWork> dentalWorksEntityList = dentalWorkRepository.findByDentist(dentist);

        List<DentalWorkModel> dentalWorkModelList = DentalWorkMapper.INSTANCE.toListModel(dentalWorksEntityList);
        return dentalWorkModelList;
    }
}
