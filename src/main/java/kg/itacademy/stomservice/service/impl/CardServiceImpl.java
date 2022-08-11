package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.entity.Card;
import kg.itacademy.stomservice.entity.DentalWork;
import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.exception.*;
import kg.itacademy.stomservice.mappers.CardMapper;
import kg.itacademy.stomservice.models.CardCreateModel;
import kg.itacademy.stomservice.models.CardModel;
import kg.itacademy.stomservice.repositories.CardRepository;
import kg.itacademy.stomservice.repositories.DentalWorkRepository;
import kg.itacademy.stomservice.repositories.DentistRepository;
import kg.itacademy.stomservice.repositories.PatientRepository;
import kg.itacademy.stomservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final DentalWorkRepository dentalWorkRepository;

    @Override
    public CardModel create(CardCreateModel cardModel) {
        if (cardModel == null) {
            throw new CardModelNullException("Create card model is null");
        }

        Card newEntity = CardMapper.INSTANCE.toEntity(cardModel);
        newEntity = cardRepository.save(newEntity);
        return CardMapper.INSTANCE.toModel(newEntity);
    }

    @Override
    public boolean update(CardModel cardModel) {
        if (cardModel == null) {
            throw new CardModelNullException("Create card model is null");
        }


        Card existCard = cardRepository
                .findById(cardModel.getId())
                .orElseThrow(() -> new CardNotFoundException("card not found by id " + cardModel.getId()));

        existCard.setNumber(cardModel.getNumber());

        Patient existPatient = patientRepository
                .findById(cardModel.getPatient().getId())
                .orElseThrow(() -> new PatientNotFoundException("patient not found by id " + cardModel.getPatient().getId()));
        existCard.setPatient(existPatient);

        Dentist existDentist = dentistRepository
                .findById(cardModel.getDentist().getId())
                .orElseThrow(() -> new DentistNotFoundException("dentist not found by id " + cardModel.getDentist().getId()));
        existCard.setDentist(existDentist);

        DentalWork existDentalWork = dentalWorkRepository
                .findById(cardModel.getDentalWork().getId())
                .orElseThrow(() -> new DentalWorkNotFoundException("dental work not found by id " + cardModel.getDentalWork().getId()));
        existCard.setDentalWork(existDentalWork);
        existCard.setPatientStatus(cardModel.getPatientStatus());
        existCard.setDebt(cardModel.getDebt());

        existCard = cardRepository.save(existCard);

        return existCard.getId() != null;


    }

    @Override
    public boolean deleteById(Long id) {
        cardRepository.deleteById(id);

        return true;
    }

    @Override
    public CardModel getByPatient(Patient patient) {
        Card cardEntity = cardRepository.findByPatient(patient);

        CardModel cardModel = CardMapper.INSTANCE.toModel(cardEntity);

        return cardModel;

    }

    @Override
    public List<CardModel> getAllByNumber(Integer number) {
        List<Card> cardEntityList = cardRepository.findAllByNumber(number);

        List<CardModel> cardModelList = CardMapper.INSTANCE.toListModel(cardEntityList);

        return cardModelList;
    }
}


