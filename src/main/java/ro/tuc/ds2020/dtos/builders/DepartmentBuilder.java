package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DepartmentDTO;
import ro.tuc.ds2020.entities.Department;

public class DepartmentBuilder {

    private DepartmentBuilder() {
    }

    public static DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getId(),department.getDepartmentName(),department.getDirector());
    }

    public static Department toEntity(DepartmentDTO departmentDTO) {
        return new Department(departmentDTO.getDepartmentName(), departmentDTO.getDirector());
    }
}
