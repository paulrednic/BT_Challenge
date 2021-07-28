package ro.tuc.ds2020.services;


import org.springframework.beans.factory.annotation.Autowired;
import ro.tuc.ds2020.dtos.DepartmentDTO;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.HolidayDTO;
import ro.tuc.ds2020.dtos.ProjectDTO;
import ro.tuc.ds2020.dtos.builders.DepartmentBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationLogic {
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final HolidayService holidayService;
    private final DepartmentService departmentService;
    private final HierarchyService hierarchyService;

    @Autowired
    public ApplicationLogic(EmployeeService employeeService, ProjectService projectService,
                            HolidayService holidayService, DepartmentService departmentService, HierarchyService hierarchyService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.holidayService = holidayService;
        this.departmentService = departmentService;
        this.hierarchyService = hierarchyService;
    }

    //first task of calculating salary
    public int calculateSalary(String employeeName) {
        int totalSalary = 0;
        //find employee by name in database
        EmployeeDTO employeeDTO = employeeService.findEmployeeByName(employeeName);
        //calculate years passed
        Date emplDate = employeeDTO.getEmploymentDate();
        long millies = Math.abs(new java.util.Date().getTime() - emplDate.getTime());
        long daysDiff = TimeUnit.DAYS.convert(millies, TimeUnit.MILLISECONDS);
        int years = (int)daysDiff/365;
        //calculate salary
        totalSalary = employeeDTO.getRole().getBaseSalary() * years;
        return totalSalary;
    }

    //second task of assigning employees to projects
    public List<String> assignToProject(List<String> employeeList, String projectName) {
        List<String> employeesOnHoliday = new ArrayList<String>();
        //find project by name
        ProjectDTO projectDTO = projectService.findProjectByName(projectName);
        Date pStart = projectDTO.getStartDate();
        Date pEnd = projectDTO.getEndDate();

        for(String employee : employeeList) {
            boolean availability = true;
            //find employee by name
            EmployeeDTO employeeDTO = employeeService.findEmployeeByName(employee);
            //get holidays of employee
            List<HolidayDTO> holidayList = holidayService.findHolidayByEmployee(employeeDTO);
            for(HolidayDTO holiday : holidayList) {
                Date hStart = holiday.getStartDate();
                Date hEnd = holiday.getEndDate();
                //check if holiday in the same time with project
                if(!(pStart.after(hEnd) || hStart.after(pEnd))){
                    availability = false;
                    employeesOnHoliday.add(employee);
                    break;
                }
            }
            if(availability = true) {
                //add person to project
                projectService.addEmployeeToProject(projectDTO, employeeDTO);
            }
        }
        return employeesOnHoliday;
    }

    //3rd task of getting employees in department
    public List<String> employeesInDepartment(String department) {
        List<String> employeesInDepartment = new ArrayList<String>();
        //search department by name
        DepartmentDTO departmentDTO = departmentService.findDepartmentByName(department);
        //search employees in department
        List<EmployeeDTO> emplList = employeeService.findEmployeeByDepartment(departmentDTO);
        for(EmployeeDTO emp : emplList) {
            employeesInDepartment.add(emp.getName());
        }
        return employeesInDepartment;
    }

    //4th task of getting all the departments responsible for a project
    public List<DepartmentDTO> departmentsInProject(ProjectDTO project) {
        List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
        //get all employees on the project
        List<EmployeeDTO> employeesOnProject = projectService.getEmployeeOfProject(project);
        for(EmployeeDTO employee : employeesOnProject) {
            //if the department has not been added before
            if(!(departments.contains(employee.getDepartment()))) {
                departments.add(DepartmentBuilder.toDepartmentDTO(employee.getDepartment()));
            }
        }
        return departments;
    }

    //5th task change two different employees
    public void changeEmployees(EmployeeDTO employeeGone, EmployeeDTO employeeCome){
        employeeService.insert(employeeCome);
        holidayService.deleteByEmployee(employeeGone); //delete all holidays of gone employee
        DepartmentDTO department = DepartmentBuilder.toDepartmentDTO(employeeGone.getDepartment());
        //delete the old one and add the new employee from department
        departmentService.deleteEmployeeFromDepartment(department,employeeGone);
        departmentService.addEmployeeToDepartment(department,employeeCome);
        //change in projects
        List<ProjectDTO> projects = projectService.findProjects();
        for (ProjectDTO projectDTO : projects) {
            projectService.deleteEmployeeFromProject(projectDTO,employeeGone);
            projectService.addEmployeeToProject(projectDTO,employeeCome);
        }
        //change in relationships
        hierarchyService.changeBosses(employeeGone, employeeCome);
        hierarchyService.changeSubordinates(employeeGone,employeeCome);
    }
}
