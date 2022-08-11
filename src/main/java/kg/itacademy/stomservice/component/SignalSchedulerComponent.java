package kg.itacademy.stomservice.component;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.entity.RecordStatus;
import kg.itacademy.stomservice.models.MailSendModel;
import kg.itacademy.stomservice.repositories.DentistsAppointmentRepository;
import kg.itacademy.stomservice.service.MailSendService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static kg.itacademy.stomservice.entity.RecordStatus.APPOINTED;
import static kg.itacademy.stomservice.entity.RecordStatus.NEW;

@Component
@AllArgsConstructor
public class SignalSchedulerComponent {

    private final DentistsAppointmentRepository dentistsAppointmentRepository;
    private final MailSendService mailSendService;

    //@Scheduled(cron = "0 0 * * * *")
    @Scheduled(fixedDelay = 60000)
    public void emailSendMethod() {
        //Получить список записей на прием
        List<DentistsAppointment> list = dentistsAppointmentRepository.findAllByRecordStatus(NEW);
        // в цикле взять первую запись
        for (DentistsAppointment element : list) {
            // вытащить стоматолога
            Dentist den = element.getDentist();
            // вытащить емейл стоматолога
            String email = den.getEmail();
            // отправить емейл и текст
            MailSendModel sendModel = new MailSendModel();
            sendModel.setText("У Вас новая запись на прием " + element.getVisitDate() + " " + element.getVisitStartTime()
            + " " + element.getPatient());
            sendModel.setTitle("Notification");
            sendModel.setReceiverEmail(email);
            mailSendService.mailSend(sendModel);
            // изменить статус документа на просрочен
            element.setRecordStatus(APPOINTED);
            // сохранить запись на прием
            dentistsAppointmentRepository.save(element);
            // цикл заканчивается
        }
    }
}
