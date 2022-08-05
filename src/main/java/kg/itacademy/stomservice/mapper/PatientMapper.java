package kg.itacademy.stomservice.mapper;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.model.DentistCreateModel;
import kg.itacademy.stomservice.model.DentistModel;
import kg.itacademy.stomservice.model.PatientCreateModel;
import kg.itacademy.stomservice.model.PatientModel;
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
