package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.builders.EmployeeBuilder;
import ro.tuc.ds2020.entities.Hierarchy;
import ro.tuc.ds2020.repositories.HierarchyRepository;

import java.util.List;

public class HierarchyService {
    private final HierarchyRepository hierarchyRepository;

    @Autowired
    public HierarchyService(HierarchyRepository hierarchyRepository) {
        this.hierarchyRepository = hierarchyRepository;
    }

    public void changeBosses(EmployeeDTO employeeGone, EmployeeDTO employeeCome) {
        List<Hierarchy> hierarchies = hierarchyRepository.findByBossId(employeeGone.getId());
        //for each found relationship, change the employee
        for(Hierarchy hierarchy : hierarchies) {
            hierarchy.setBoss(EmployeeBuilder.toEntity(employeeCome));
            hierarchyRepository.save(hierarchy);
        }
    }

    public void changeSubordinates(EmployeeDTO employeeGone, EmployeeDTO employeeCome) {
        List<Hierarchy> hierarchies = hierarchyRepository.findBySubordinateId(employeeGone.getId());
        //for each found relationship, change the employee
        for(Hierarchy hierarchy : hierarchies) {
            hierarchy.setSubordinate(EmployeeBuilder.toEntity(employeeCome));
            hierarchyRepository.save(hierarchy);
        }
    }
}
