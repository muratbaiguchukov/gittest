package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.models.PatientCreateModel;
import kg.itacademy.stomservice.models.PatientModel;
import kg.itacademy.stomservice.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "/api/patient")
@Slf4j
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping(path = "/create")
    public ResponseEntity<PatientModel> createNewPatient(@Valid @RequestBody PatientCreateModel patientModel) { //фронт дает задание создать нового пациента
        PatientModel result = patientService.create(patientModel);// мы эту модельку (patientModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updatePatient(@Valid @RequestBody PatientModel patientModel) {// пришла моделька(PatientModel) от фронта
        patientService.update(patientModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(patientService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByPatientLastName")
    public ResponseEntity<List<PatientModel>> getByAllPatientLastName(@NotBlank @RequestParam("allPatientLastName") String patientLastName) {
        try {
            return ResponseEntity.ok(patientService.getAllByPatientLastName(patientLastName));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
