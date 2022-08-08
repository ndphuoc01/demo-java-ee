package com.axonactive.companyjavaee.service;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentDAO departmentDAO;

    @Mock
    DepartmentMapper departmentMapper;

    List<Department> departments = new ArrayList<>();
    Department department1 = new Department(1, "Admin", LocalDate.of(2022, 8, 8));
    Department department2 = new Department(2, "IT", LocalDate.of(2022, 4, 4));
    DepartmentDto departmentDto1 = new DepartmentDto(1, "Admin", LocalDate.of(2022, 8, 8));
    DepartmentDto departmentDto2 = new DepartmentDto(2, "IT", LocalDate.of(2022, 4, 4));


    @BeforeEach
    void setUp() {
        departments.add(department1);
        departments.add(department2);

    }


    @Test
    void testGetAll_shouldReturnData_whenUsed() {
        List<DepartmentDto> expectedDepartmentList = new ArrayList<>();
        expectedDepartmentList.add(departmentDto1);
        expectedDepartmentList.add(departmentDto2);

        when(departmentDAO.findAll()).thenReturn(departments);
        when(departmentMapper.toDtos(departments)).thenReturn(expectedDepartmentList);
        List<DepartmentDto> actualDepartments = departmentService.getAll();

        assertEquals(expectedDepartmentList.size(), actualDepartments.size());
    }

    @Test
    void testFindById_shouldReturn1_whenInput1() {
        Department expectedDepartment = department1;
        when(departmentDAO.findById(1)).thenReturn(expectedDepartment);
        when(departmentMapper.toDto(expectedDepartment)).thenReturn(departmentDto1);
        DepartmentDto actualDepartment = departmentService.findById(1);
        assertEquals(expectedDepartment.getDepartmentId(), actualDepartment.getDepartmentId());
    }

    @Test
    void testFindById_shouldThrowException_whenInputNonExist() {
        when(departmentDAO.findById(100)).thenThrow(new NoResultException("No department found"));
        assertThrows(NoResultException.class, () -> {
            departmentService.findById(100);
        });
    }
}
