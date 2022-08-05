package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.WorkShiftCreateModel;
import kg.itacademy.stomservice.model.WorkShiftModel;
import kg.itacademy.stomservice.service.WorkShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/workShift")
@Slf4j
public class WorkShiftController {

    @Autowired
    WorkShiftService workShiftService;

    @PostMapping(path = "/create")
    public ResponseEntity<WorkShiftModel> createNewWorkShift(@Valid @RequestBody WorkShiftCreateModel workShiftModel) { //фронт дает задание создать новое расписание стоматолога
        WorkShiftModel result = workShiftService.create(workShiftModel);// мы эту модельку (workShiftModel) передаем в сервисный слой

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
    public ResponseEntity<Boolean> updateWorkShift(@Valid @RequestBody WorkShiftModel workShiftModel) {// пришла моделька(WorkShiftModel) от фронта
        workShiftService.update(workShiftModel);
        return ResponseEntity.ok(true);

    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@Valid @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(workShiftService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByWorkShiftDentist")
    public ResponseEntity<List<WorkShiftModel>> getByAllDentist(@NotBlank @RequestParam("allDentist") Dentist dentist) {
        try {
            return ResponseEntity.ok(workShiftService.getAllByDentist(dentist));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/getAllByWorkShiftDay")
    public ResponseEntity<List<WorkShiftModel>> getByAllWorkShiftDay(@NotBlank @RequestParam("allDay") LocalDate workShiftDay) {
        try {
            return ResponseEntity.ok(workShiftService.getAllByWorkShiftDay(workShiftDay));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
//
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException{
//                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//
//            @Override
//            public String getAsText() throws IllegalArgumentException {
//                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
//            }
//        });
//    }

}


