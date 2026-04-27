import java.time.LocalDate;

public class SprintController {

    public Sprint createSprint(String name, LocalDate startDate, LocalDate endDate) {
        return new Sprint(name, startDate, endDate);
    }

//    public void saveSprint(Sprint sprint) {
//        return;
//    }
}
