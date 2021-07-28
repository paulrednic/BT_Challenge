package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.ProjectDTO;
import ro.tuc.ds2020.dtos.builders.EmployeeBuilder;
import ro.tuc.ds2020.dtos.builders.ProjectBuilder;
import ro.tuc.ds2020.entities.Employee;
import ro.tuc.ds2020.entities.Project;
import ro.tuc.ds2020.repositories.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> findProjects() {
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(ProjectBuilder::toProjectDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findProjectByName(String name) {
        List<Project> project = projectRepository.findByName(name);
        return ProjectBuilder.toProjectDTO(project.get(0));
    }

    public ProjectDTO addEmployeeToProject(ProjectDTO projectDTO, EmployeeDTO employeeDTO) {
        Project project = projectRepository.findById(projectDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        List<Employee> employees = project.getEmployees(); //initial list of employees on project
        employees.add(EmployeeBuilder.toEntity(employeeDTO)); //add new employee
        project.setEmployees(employees); //add updated list of employees
        projectRepository.save(project);
        System.out.println("Project updated in db");
        return ProjectBuilder.toProjectDTO(project);
    }

    public ProjectDTO deleteEmployeeFromProject(ProjectDTO projectDTO, EmployeeDTO employeeDTO) {
        Project project = projectRepository.findById(projectDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        List<Employee> employees = project.getEmployees(); //initial list of employees on project
        for (Employee employee : employees) {
            if(employee.getId() == employeeDTO.getId())
                employees.remove(employee);
        }
        project.setEmployees(employees); //add updated list of employees
        projectRepository.save(project);
        System.out.println("Project updated in db");
        return ProjectBuilder.toProjectDTO(project);
    }

    public List<EmployeeDTO> getEmployeeOfProject(ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        List<Employee> employees = project.getEmployees(); //initial list of employees on project
        return employees.stream()
                .map(EmployeeBuilder::toEmployeeDTO)
                .collect(Collectors.toList());
    }

}
