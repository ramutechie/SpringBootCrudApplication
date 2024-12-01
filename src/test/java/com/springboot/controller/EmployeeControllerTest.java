package com.springboot.controller;

import com.springboot.dto.EmployeeDTO;
import com.springboot.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeDTO = new EmployeeDTO(1L, "John Doe", "Engineering", 75000.0);
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployeeDTOs() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeDTO));

        List<EmployeeDTO> employees = employeeController.getAllEmployees();

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals(employeeDTO, employees.get(0));
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void getEmployeeById_ShouldReturnEmployeeDTO() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);

        assertNotNull(response.getBody());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void createEmployee_ShouldReturnCreatedEmployeeDTO() {
        when(employeeService.createEmployee(employeeDTO)).thenReturn(employeeDTO);

        EmployeeDTO result = employeeController.createEmployee(employeeDTO);

        assertNotNull(result);
        assertEquals(employeeDTO, result);
        verify(employeeService, times(1)).createEmployee(employeeDTO);
    }

    @Test
    void updateEmployee_ShouldReturnUpdatedEmployeeDTO() {
        EmployeeDTO updatedDTO = new EmployeeDTO(1L, "Jane Doe", "HR", 80000.0);
        when(employeeService.updateEmployee(1L, updatedDTO)).thenReturn(updatedDTO);

        EmployeeDTO result = employeeController.updateEmployee(1L, updatedDTO);

        assertNotNull(result);
        assertEquals("Jane Doe", result.name());
        assertEquals("HR", result.department());
        verify(employeeService, times(1)).updateEmployee(1L, updatedDTO);
    }
}
