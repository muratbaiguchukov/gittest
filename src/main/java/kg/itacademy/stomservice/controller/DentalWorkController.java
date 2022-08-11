package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.models.DentalWorkCreateModel;
import kg.itacademy.stomservice.models.DentalWorkModel;
import kg.itacademy.stomservice.service.DentalWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dentalWork")
@Slf4j
public class DentalWorkController {

    @Autowired
    DentalWorkService dentalWorkService;

    @PostMapping(path = "/create")
    public ResponseEntity<DentalWorkModel> createNewDentalWork(@Valid @RequestBody DentalWorkCreateModel dentalWorkModel) { //фронт дает задание создать новую стоматологическую работу
        DentalWorkModel result = dentalWorkService.create(dentalWorkModel);// мы эту модельку (dentalWorkModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updateDentalWork(@Valid @RequestBody DentalWorkModel dentalWorkModel) {// пришла моделька(DentalWorkModel) от фронта
        dentalWorkService.update(dentalWorkModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(dentalWorkService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByDentalWorkName")
    public ResponseEntity<List<DentalWorkModel>> getAllByDentalWorkName(@NotBlank @RequestParam("AllByDentalWorkName") String dentalWorkName) {
        try {
            return ResponseEntity.ok(dentalWorkService.getAllByDentalWorkName(dentalWorkName));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping(path = "/getAllByDentist")
    public ResponseEntity<List<DentalWorkModel>> getAllByDentist(@NotBlank @RequestParam("allByDentist") Dentist dentist) {
        try {
            return ResponseEntity.ok(dentalWorkService.getAllByDentist(dentist));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
