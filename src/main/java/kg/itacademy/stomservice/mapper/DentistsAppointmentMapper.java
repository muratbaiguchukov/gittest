package kg.itacademy.stomservice.mapper;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.model.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.model.DentistsAppointmentModel;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface DentistsAppointmentMapper {

    DentistsAppointmentMapper INSTANCE = Mappers.getMapper(DentistsAppointmentMapper.class);

    DentistsAppointmentModel toModel(DentistsAppointment dentistsAppointment);

    DentistsAppointment toEntity(DentistsAppointmentModel dentistsAppointment);
    DentistsAppointment toEntity(DentistsAppointmentCreateModel dentistsAppointment);

    List<DentistsAppointmentModel> toListModel(List<DentistsAppointment> dentistsAppointments);
}
