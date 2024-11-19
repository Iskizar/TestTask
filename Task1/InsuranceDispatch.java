package Task1;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class InsuranceDispatch {

    public LocalDateTime getNextDispatchDate() {
        LocalDate today = LocalDate.now();
        LocalTime dispatchTime = LocalTime.of(18, 0);

        // Возможные даты отправки
        LocalDate[] dispatchDates = {
                today.withDayOfMonth(1),
                today.withDayOfMonth(10),
                today.withDayOfMonth(20)
        };

        // Находим ближайшую дату отправки
        LocalDate nextDispatchDate = Arrays.stream(dispatchDates)
                .filter(date -> !today.isAfter(date))
                .min(LocalDate::compareTo)
                .orElse(today.plusMonths(1).withDayOfMonth(1));

        // Коррекция даты на рабочий день
        Date adjustedDate = getVacCheck(Date.valueOf(nextDispatchDate));
        LocalDate finalDispatchDate = adjustedDate.toLocalDate();

        return LocalDateTime.of(finalDispatchDate, dispatchTime);
    }

    // Пример функции getVacCheck
    public Date getVacCheck(Date modDate) {
        LocalDate localDate = modDate.toLocalDate();

        // Если дата выпадает на выходной, сдвигаем на следующий рабочий день
        if (localDate.getDayOfWeek().getValue() == 6) {
            return Date.valueOf(localDate.plusDays(2));
        } else if (localDate.getDayOfWeek().getValue() == 7) {
            return Date.valueOf(localDate.plusDays(1));
        }
        return modDate;
    }


}
