package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.entities.Employee;

public class EmployeeBuilder {

    private EmployeeBuilder() {
    }

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getRole(),
                employee.getDepartment(), employee.getEmploymentDate());
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getName(), employeeDTO.getRole(),
                employeeDTO.getEmploymentDate(), employeeDTO.getDepartment());
    }
}
