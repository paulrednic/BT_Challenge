package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Employee;
import ro.tuc.ds2020.entities.Holiday;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HolidayRepository extends JpaRepository<Holiday, UUID> {
    List<Holiday> findByEmployee(Employee employee);

    @Query(value = "DELETE " +
            "FROM Holiday " +
            "WHERE employee_id = :employee_id ")
    Optional<Employee> deleteByEmployeeId(@Param("employee_id") UUID employeeId);
}
