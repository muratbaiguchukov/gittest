package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.models.CardCreateModel;
import kg.itacademy.stomservice.models.CardModel;
import kg.itacademy.stomservice.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "/api/card")
@Slf4j
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping(path = "/create")
    public ResponseEntity<CardModel> createNewCard(@Valid @RequestBody CardCreateModel cardModel) { //фронт дает задание создать новую карточку
        CardModel result = cardService.create(cardModel);// мы эту модельку (cardModel) передаем в сервисный слой

        if (result.getId() != null) { // если при создании поля id что-то есть, значит мы создали успешно и можем вернуть 201

            return ResponseEntity.status(HttpStatus.CREATED).body(result); //то вернется со статусом 201 или CREATED
        } else { // иначе
            log.info("Id is empty");
            return ResponseEntity // то вернется со статусом 500 если поле id пустое
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateCard(@Valid @RequestBody CardModel cardModel) {// пришла моделька(CardModel) от фронта
        cardService.update(cardModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(cardService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getByPatient")
    public ResponseEntity<CardModel> getByPatient(@NotBlank @RequestParam("patient") Patient patient) {
        try {
            return ResponseEntity.ok(cardService.getByPatient(patient));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping(path = "/getAllByNumber")
    public ResponseEntity<List<CardModel>> getByAllNumber(@NotBlank @RequestParam("allNumber") Integer number) {
        try {
            return ResponseEntity.ok(cardService.getAllByNumber(number));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
