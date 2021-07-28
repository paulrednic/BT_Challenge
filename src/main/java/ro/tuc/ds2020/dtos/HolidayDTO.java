package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Employee;

import java.util.Date;
import java.util.UUID;

public class HolidayDTO extends RepresentationModel<HolidayDTO> {
    private UUID id;
    private Employee employee;
    private Date startDate;
    private Date endDate;

    public HolidayDTO() {
    }

    public HolidayDTO(UUID id, Employee employee, Date startDate, Date endDate) {
        this.id = id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
