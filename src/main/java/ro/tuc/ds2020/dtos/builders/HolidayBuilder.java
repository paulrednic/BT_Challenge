package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.HolidayDTO;
import ro.tuc.ds2020.entities.Holiday;

public class HolidayBuilder {

    private HolidayBuilder() {
    }

    public static HolidayDTO toHolidayDTO(Holiday holiday) {
        return new HolidayDTO(holiday.getId(), holiday.getEmployee(), holiday.getStartDate(), holiday.getEndDate());
    }

    public static Holiday toEntity(HolidayDTO holidayDTO) {
        return new Holiday(holidayDTO.getEmployee(), holidayDTO.getStartDate(), holidayDTO.getEndDate());
    }
}
