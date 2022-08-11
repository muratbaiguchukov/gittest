package kg.itacademy.stomservice.mappers;

import kg.itacademy.stomservice.entity.AvailableTimeSlot;
import kg.itacademy.stomservice.models.AvailableTimeSlotCreateModel;
import kg.itacademy.stomservice.models.AvailableTimeSlotModel;
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
