package kg.itacademy.stomservice.mappers;

import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.models.PatientCreateModel;
import kg.itacademy.stomservice.models.PatientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientModel toModel(Patient patient);

    Patient toEntity(PatientModel patient);
    Patient toEntity(PatientCreateModel patient);

    List<PatientModel> toListModel(List<Patient> patient);

}
