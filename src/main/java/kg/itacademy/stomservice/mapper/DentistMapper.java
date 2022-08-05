package kg.itacademy.stomservice.mapper;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DentistMapper {
    DentistMapper INSTANCE = Mappers.getMapper(DentistMapper.class);

    DentistModel toModel(Dentist dentist);

    Dentist toEntity(DentistModel dentist);
    Dentist toEntity(DentistCreateModel dentist);

    List<DentistModel> toListModel(List<Dentist> dentist);
}



