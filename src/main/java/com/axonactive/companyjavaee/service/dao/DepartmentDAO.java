package com.axonactive.companyjavaee.service.dao;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface DepartmentDAO {
    List<Department> findAll();

    Department save(DepartmentRequest departmentRequest);

    void update(Department department);

    void flush();

    void removeEntity(Department department);

    Department findById(Integer id);

    void remove(Integer id);

    Department findByDepartmentName(String name);
}
