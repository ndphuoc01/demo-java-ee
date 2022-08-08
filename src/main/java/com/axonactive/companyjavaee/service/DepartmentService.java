package com.axonactive.companyjavaee.service;

import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.DataOutput;
import java.util.List;

@Stateless
public class DepartmentService {
    @Inject
    private DepartmentDAO departmentDAO;

    @Inject
    private DepartmentMapper departmentMapper;

    public List<DepartmentDto> getAll() {
        return departmentMapper.toDtos(departmentDAO.findAll());
    }

    public DepartmentDto findById(Integer id) {
        return departmentMapper.toDto(departmentDAO.findById(id));
    }

    public DepartmentDto save(DepartmentRequest departmentRequest) {
        return departmentMapper.toDto(departmentDAO.save(departmentRequest));
    }

//    public DepartmentDto update(Integer id, DepartmentRequest request){
//        return departmentMapper.toDto(departmentDAO.update();)
//    }
}
