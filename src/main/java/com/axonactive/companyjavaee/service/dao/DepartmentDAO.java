package com.axonactive.companyjavaee.service.dao;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.rest.request.DepartmentRequest;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface DepartmentDAO {
    List<Department> findAll();

    Department save(DepartmentRequest departmentRequest);

    Department update(Integer id, DepartmentRequest departmentRequest);

    void flush();

    void removeEntity(Department department);

    Department findById(Integer id);

    void remove(Integer id);

    Department findByDepartmentName(String name);
}
