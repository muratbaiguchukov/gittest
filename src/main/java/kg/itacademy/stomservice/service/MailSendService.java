package kg.itacademy.stomservice.service;


import kg.itacademy.stomservice.models.MailSendModel;

public interface MailSendService {
    boolean mailSend(MailSendModel mailSendModel);
}