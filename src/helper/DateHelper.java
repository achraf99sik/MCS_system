package helper;

import java.time.LocalDate;

public class DateHelper {
    public Integer getAge(LocalDate date){
        return LocalDate.now().compareTo(date);
    }
}
