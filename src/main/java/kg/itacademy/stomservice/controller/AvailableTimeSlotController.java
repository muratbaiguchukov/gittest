package kg.itacademy.stomservice.controller;

import com.sun.istack.NotNull;
import kg.itacademy.stomservice.models.AvailableTimeSlotCreateModel;
import kg.itacademy.stomservice.models.AvailableTimeSlotModel;
import kg.itacademy.stomservice.service.AvailableTimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/availableTimeSlot")
@Slf4j
public class AvailableTimeSlotController {

    @Autowired
    AvailableTimeSlotService availableTimeSlotService;

    @PostMapping(path = "/create")
    public ResponseEntity<AvailableTimeSlotModel> createNewAvailableTimeSlot(@Valid @RequestBody AvailableTimeSlotCreateModel availableTimeSlotModel) { //фронт дает задание создать новые доступные временные слоты
        AvailableTimeSlotModel result = availableTimeSlotService.create(availableTimeSlotModel);// мы эту модельку (availableTimeSlotModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updateAvailableTimeSlot(@Valid @RequestBody AvailableTimeSlotModel availableTimeSlotModel) {// пришла моделька(AvailableTimeSlotModel) от фронта
        availableTimeSlotService.update(availableTimeSlotModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(availableTimeSlotService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAvailableTimeSlotByDentistIdInPeriod")
    public ResponseEntity<List<AvailableTimeSlotModel>> getAvailableTimeSlotByDentistIdInPeriod(@NotNull @RequestParam("dentistId") Long dentistId, LocalDate startDate, LocalDate endDate) {
        try {
            return ResponseEntity.ok(availableTimeSlotService.getAvailableTimeSlotByDentistIdInPeriod(dentistId, startDate, endDate));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
