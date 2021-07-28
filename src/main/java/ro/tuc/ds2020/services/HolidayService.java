package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import ro.tuc.ds2020.dtos.EmployeeDTO;
import ro.tuc.ds2020.dtos.HolidayDTO;
import ro.tuc.ds2020.dtos.builders.EmployeeBuilder;
import ro.tuc.ds2020.dtos.builders.HolidayBuilder;
import ro.tuc.ds2020.entities.Holiday;
import ro.tuc.ds2020.repositories.HolidayRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HolidayService {
    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }


    public List<HolidayDTO> findHolidayByEmployee(EmployeeDTO employee) {
        List<Holiday> holidays = holidayRepository.findByEmployee(EmployeeBuilder.toEntity(employee));
        return holidays.stream()
                .map(HolidayBuilder::toHolidayDTO)
                .collect(Collectors.toList());
    }

    public void deleteByEmployee(EmployeeDTO employee) {
        holidayRepository.deleteByEmployeeId(employee.getId());
        System.out.println("holiday deleted");
    }
}
