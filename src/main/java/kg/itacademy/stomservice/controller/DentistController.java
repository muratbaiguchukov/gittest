package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.models.DentistCreateModel;
import kg.itacademy.stomservice.models.DentistModel;
import kg.itacademy.stomservice.service.DentistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dentist")
@Slf4j
public class DentistController {

    @Autowired
    DentistService dentistService;

    @PostMapping(path = "/create")
    public ResponseEntity<DentistModel> createNewDentist(@Valid @RequestBody DentistCreateModel dentistModel) { //фронт дает задание создать нового стоматолога
        DentistModel result = dentistService.create(dentistModel);// мы эту модельку (dentistModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updateDentist(@Valid @RequestBody DentistModel dentistModel) {// пришла моделька(DentistModel) от фронта
        dentistService.update(dentistModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(dentistService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByDentistLastName")
    public ResponseEntity<List<DentistModel>> getByAllDentistLastName(@NotBlank @RequestParam("allDentistLastName") String dentistLastName) {
        try {
            return ResponseEntity.ok(dentistService.getAllByDentistLastName(dentistLastName));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<DentistModel>> getAll() {
        try {
            return ResponseEntity.ok(dentistService.getAll());
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

   }