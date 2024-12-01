package com.springboot.service;

import com.springboot.dto.EmployeeDTO;
import com.springboot.model.Employee;
import com.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment("Engineering");
        employee.setSalary(75000.0);

        employeeDTO = new EmployeeDTO(1L, "John Doe", "Engineering", 75000.0);
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployeeDTOs() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals(employeeDTO, employees.get(0));
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById_ShouldReturnEmployeeDTO_WhenEmployeeExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(employeeDTO, result);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_ShouldThrowException_WhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> employeeService.getEmployeeById(1L));

        assertEquals("Employee not found with id: 1", exception.getMessage());
    }

    @Test
    void createEmployee_ShouldReturnEmployeeDTO() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.createEmployee(employeeDTO);

        assertNotNull(result);
        assertEquals(employeeDTO, result);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_ShouldReturnUpdatedEmployeeDTO() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO updatedDTO = new EmployeeDTO(1L, "Jane Doe", "HR", 80000.0);
        EmployeeDTO result = employeeService.updateEmployee(1L, updatedDTO);

        assertNotNull(result);
        assertEquals(updatedDTO.name(), result.name());
        assertEquals(updatedDTO.department(), result.department());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}
