package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ProjectDTO;
import ro.tuc.ds2020.entities.Project;

public class ProjectBuilder {

    private ProjectBuilder() {
    }

    public static ProjectDTO toProjectDTO(Project project) {
        return new ProjectDTO(project.getId(), project.getProjectName(), project.getStartDate(), project.getEndDate());
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        return new Project(projectDTO.getName(), projectDTO.getStartDate(), projectDTO.getEndDate());
    }
}
