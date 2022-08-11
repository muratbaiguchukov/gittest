package kg.itacademy.stomservice.mappers;

import kg.itacademy.stomservice.entity.DentistsAppointment;
import kg.itacademy.stomservice.models.DentistsAppointmentCreateModel;
import kg.itacademy.stomservice.models.DentistsAppointmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface DentistsAppointmentMapper {

    DentistsAppointmentMapper INSTANCE = Mappers.getMapper(DentistsAppointmentMapper.class);

    DentistsAppointmentModel toModel(DentistsAppointment dentistsAppointment);

    DentistsAppointment toEntity(DentistsAppointmentModel dentistsAppointment);
    DentistsAppointment toEntity(DentistsAppointmentCreateModel dentistsAppointment);

    List<DentistsAppointmentModel> toListModel(List<DentistsAppointment> dentistsAppointments);
}
