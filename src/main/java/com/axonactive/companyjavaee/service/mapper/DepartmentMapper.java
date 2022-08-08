package com.axonactive.companyjavaee.service.mapper;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);

    List<DepartmentDto> toDtos(List<Department> departments);
}
