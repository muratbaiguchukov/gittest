package kg.itacademy.stomservice.mapper;

import kg.itacademy.stomservice.entity.AvailableTimeSlot;
import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.AvailableTimeSlotCreateModel;
import kg.itacademy.stomservice.model.AvailableTimeSlotModel;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface AvailableTimeSlotMapper {

    AvailableTimeSlotMapper INSTANCE = Mappers.getMapper(AvailableTimeSlotMapper.class);

    AvailableTimeSlotModel toModel(AvailableTimeSlot availableTimeSlot);

    AvailableTimeSlot toEntity(AvailableTimeSlotModel availableTimeSlot);
    AvailableTimeSlot toEntity(AvailableTimeSlotCreateModel availableTimeSlot);

    List<AvailableTimeSlotModel> toListModel(List<AvailableTimeSlot> availableTimeSlots);
}
