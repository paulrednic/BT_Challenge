package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DepartmentDTO;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.builders.DepartmentBuilder;
import ro.tuc.ds2020.dtos.builders.EmployeeBuilder;
import ro.tuc.ds2020.entities.Department;
import ro.tuc.ds2020.entities.Employee;
import ro.tuc.ds2020.repositories.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> findDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream()
                .map(DepartmentBuilder::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO findDepartmentByName(String name) {
        List<Department> departments = departmentRepository.findByName(name);
        return DepartmentBuilder.toDepartmentDTO(departments.get(0));
    }

    public DepartmentDTO addEmployeeToDepartment(DepartmentDTO departmentDTO, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        List<Employee> employees = department.getEmployees(); //initial list of employees in department
        employees.add(EmployeeBuilder.toEntity(employeeDTO)); //add new employee
        department.setEmployees(employees); //add updated list of employees
        departmentRepository.save(department);
        System.out.println("Department updated in db");
        return DepartmentBuilder.toDepartmentDTO(department);
    }

    public DepartmentDTO deleteEmployeeFromDepartment(DepartmentDTO departmentDTO, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        List<Employee> employees = department.getEmployees(); //initial list of employees on project
        for (Employee employee : employees) {
            if(employee.getId() == employeeDTO.getId())
                employees.remove(employee);
        }
        department.setEmployees(employees); //add updated list of employees
        departmentRepository.save(department);
        System.out.println("Department updated in db");
        return DepartmentBuilder.toDepartmentDTO(department);
    }

}
