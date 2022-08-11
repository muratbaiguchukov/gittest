package kg.itacademy.stomservice.service;

import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.models.CardCreateModel;
import kg.itacademy.stomservice.models.CardModel;

import java.util.List;

public interface CardService {

    CardModel create(CardCreateModel cardModel); //создать карточку

    boolean update(CardModel cardModel); //изменить карточку

    boolean deleteById(Long id); //удалить карточку по id

    CardModel getByPatient(Patient patient); //получить все карточки по пациентам

    List<CardModel> getAllByNumber(Integer number); // получить все карточки по номерам
}
