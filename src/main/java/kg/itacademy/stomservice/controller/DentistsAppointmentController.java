package kg.itacademy.stomservice.controller;

import com.sun.istack.NotNull;
import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.models.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.models.DentistsAppointmentModel;
import kg.itacademy.stomservice.service.DentistsAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/dentistsAppointment")
@Slf4j
public class DentistsAppointmentController {

    @Autowired
    DentistsAppointmentService dentistsAppointmentService;

    @PostMapping(path = "/create")
    public ResponseEntity<DentistsAppointmentModel> createNewDentistsAppointment(@Valid @RequestBody DentistsAppointmentCreateModel dentistsAppointmentModel) { //фронт дает задание создать новую запись к стоматологу
        DentistsAppointmentModel result = dentistsAppointmentService.create(dentistsAppointmentModel);// мы эту модельку (dentistsAppointmentModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updateDentistsAppointment(@Valid @RequestBody DentistsAppointmentModel dentistsAppointmentModel) {// пришла моделька(DentistsAppointmentModel) от фронта
        dentistsAppointmentService.update(dentistsAppointmentModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(dentistsAppointmentService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByVisitDate")
    public ResponseEntity<List<DentistsAppointmentModel>> getAllByVisitDate(@NotNull @RequestParam("allVisitDate") LocalDate visitDate) {
        try {
            return ResponseEntity.ok(dentistsAppointmentService.getAllByVisitDate(visitDate));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByDentistsAppointmentVisitStartTime")
    public ResponseEntity<List<DentistsAppointmentModel>> getByAllDentistsAppointmentVisitStartTime(@NotBlank @RequestParam("allDentistsAppointmentVisitStartTime") LocalTime visitStartTime) {
        try {
            return ResponseEntity.ok(dentistsAppointmentService.getAllByVisitStartTime(visitStartTime));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getByDentistsAppointmentPatient")
    public ResponseEntity<DentistsAppointmentModel> getByDentistsAppointmentPatient(@NotBlank @RequestParam("patient") Patient patient) {
        try {
            return ResponseEntity.ok(dentistsAppointmentService.getByPatient(patient));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getByDentistsAppointmentDentist")
    public ResponseEntity<DentistsAppointmentModel> getByDentistsAppointmentDentist(@NotBlank @RequestParam("dentist") Dentist dentist) {
        try {
            return ResponseEntity.ok(dentistsAppointmentService.getByDentist(dentist));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
