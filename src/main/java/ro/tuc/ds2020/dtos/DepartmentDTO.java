package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Employee;
import java.util.UUID;

public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private UUID id;
    private String departmentName;
    private Employee director;

    public DepartmentDTO() {
    }

    public DepartmentDTO(UUID id, String departmentName, Employee director) {
        this.id = id;
        this.departmentName = departmentName;
        this.director = director;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }
}
