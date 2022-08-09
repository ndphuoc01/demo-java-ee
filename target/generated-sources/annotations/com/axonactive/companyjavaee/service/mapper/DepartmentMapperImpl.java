package com.axonactive.companyjavaee.service.mapper;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T17:50:52+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@ApplicationScoped
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setDepartmentId( department.getDepartmentId() );
        departmentDto.setDepartmentName( department.getDepartmentName() );
        departmentDto.setStartDate( department.getStartDate() );

        return departmentDto;
    }

    @Override
    public List<DepartmentDto> toDtos(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentDto> list = new ArrayList<DepartmentDto>( departments.size() );
        for ( Department department : departments ) {
            list.add( toDto( department ) );
        }

        return list;
    }
}
