package com.springboot.service;

import com.springboot.dto.EmployeeDTO;
import com.springboot.model.Employee;
import com.springboot.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()))
                .toList();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()))
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.name());
        employee.setDepartment(employeeDTO.department());
        employee.setSalary(employeeDTO.salary());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(), savedEmployee.getSalary());
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setName(employeeDTO.name());
        employee.setDepartment(employeeDTO.department());
        employee.setSalary(employeeDTO.salary());
        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(updatedEmployee.getId(), updatedEmployee.getName(), updatedEmployee.getDepartment(), updatedEmployee.getSalary());
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
