package Task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task1Main{
    public static void main(String[] args) {
        InsuranceDispatch dispatch = new InsuranceDispatch();
        LocalDateTime nextDispatchDate = dispatch.getNextDispatchDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Ближайшая дата отправки: " + nextDispatchDate.format(formatter));
    }
}