package com.axonactive.companyjavaee.service;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentDAO departmentDAO;

    @Mock
    private DepartmentMapper departmentMapper;

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

    @Test
    void testSave_sizeShouldIncrease_whenSaveNewRequest() {
        Department department3 = new Department();
        department3.setDepartmentName("MKT");
        department3.setStartDate(LocalDate.of(2022, 1, 1));
        DepartmentRequest departmentRequest = new DepartmentRequest("MKT", LocalDate.of(2022, 1, 1));
        DepartmentDto departmentDto3 = new DepartmentDto(3, "MKT", LocalDate.of(2022, 1, 1));

        when(departmentDAO.save(departmentRequest)).thenReturn(department3);
        when(departmentMapper.toDto(department3)).thenReturn(departmentDto3);

        DepartmentDto expectedDepartment = departmentService.save(departmentRequest);

        assertEquals(departmentDto3.getDepartmentId(), expectedDepartment.getDepartmentId());
    }


    @Test
    void testDelete_shouldExecuteOneTime_whenDeleteExisted() {
        Department toBeDeleteDepartment = new Department(123, "NoNo", LocalDate.of(2022, 4, 4));
        lenient().when(departmentDAO.findById(123)).thenReturn(toBeDeleteDepartment);
        departmentService.delete(123);
        verify(departmentDAO, times(1)).remove(123);
    }

    @Test
    void testUpdate_shouldReturnNewName_whenUpdateExistedOne() {

        DepartmentRequest updateRequest = new DepartmentRequest("Sale", LocalDate.of(2020, 1, 1));
        DepartmentDto expectUpdateDepartment = new DepartmentDto(1, "Sale", LocalDate.of(2020, 1, 1));

        when(departmentDAO.update(1, updateRequest)).thenReturn(department1);
        when(departmentMapper.toDto(department1)).thenReturn(expectUpdateDepartment);

        DepartmentDto actualUpdatedDepartment = departmentService.update(1, updateRequest);

        assertEquals(actualUpdatedDepartment.getDepartmentName(), expectUpdateDepartment.getDepartmentName());
        assertEquals(actualUpdatedDepartment.getStartDate(), expectUpdateDepartment.getStartDate());
    }
}
