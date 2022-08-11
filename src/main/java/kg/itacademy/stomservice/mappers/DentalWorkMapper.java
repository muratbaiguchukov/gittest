package kg.itacademy.stomservice.mappers;

import kg.itacademy.stomservice.entity.DentalWork;
import kg.itacademy.stomservice.models.DentalWorkCreateModel;
import kg.itacademy.stomservice.models.DentalWorkModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DentalWorkMapper {

    DentalWorkMapper INSTANCE = Mappers.getMapper(DentalWorkMapper.class);

    DentalWorkModel toModel(DentalWork dentalWork);

    DentalWork toEntity(DentalWorkModel dentalWork);
    DentalWork toEntity(DentalWorkCreateModel dentalWork);

    List<DentalWorkModel> toListModel(List<DentalWork> dentalWorks);
}
