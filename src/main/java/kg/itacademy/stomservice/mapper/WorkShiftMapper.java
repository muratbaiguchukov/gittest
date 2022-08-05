package kg.itacademy.stomservice.mapper;

import kg.itacademy.stomservice.entity.WorkShift;
import kg.itacademy.stomservice.model.WorkShiftCreateModel;
import kg.itacademy.stomservice.model.WorkShiftModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface WorkShiftMapper {

    WorkShiftMapper INSTANCE = Mappers.getMapper(WorkShiftMapper.class);

    WorkShiftModel toModel(WorkShift workShift);

    WorkShift toEntity(WorkShiftModel workShift);
    WorkShift toEntity(WorkShiftCreateModel workShift);

    List<WorkShiftModel> toListModel(List<WorkShift> workShifts);
}
