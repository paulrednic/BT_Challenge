package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Department;
import ro.tuc.ds2020.entities.Role;

import java.util.Date;
import java.util.UUID;

public class EmployeeDTO extends RepresentationModel<EmployeeDTO> {
    private UUID id;
    private String name;
    private Role role;
    private Department department;
    private Date employmentDate;

    public EmployeeDTO() {
    }

    public EmployeeDTO(UUID id, String name, Role role, Department department, Date employmentDate) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.department = department;
        this.employmentDate = employmentDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}

