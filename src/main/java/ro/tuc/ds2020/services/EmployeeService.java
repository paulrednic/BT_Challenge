package ro.tuc.ds2020.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DepartmentDTO;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.builders.EmployeeBuilder;
import ro.tuc.ds2020.entities.Employee;
import ro.tuc.ds2020.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO findEmployeeByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        return EmployeeBuilder.toEmployeeDTO(employees.get(0));
    }

    public List<EmployeeDTO> findEmployeeByDepartment(DepartmentDTO department) {
        Optional<Employee> employees = employeeRepository.findByDepartmentId(department.getId());
        if (!employees.isPresent()) {
            System.out.println("No employee found");
        }

        return employees.stream()
                .map(EmployeeBuilder::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public UUID insert(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeBuilder.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        System.out.println("Employee inserted");
        return employee.getId();
    }

}
