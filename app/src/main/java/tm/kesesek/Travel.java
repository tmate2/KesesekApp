package tm.kesesek;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class Travel {

    private final LocalTime scheduledArrive;
    private final LocalTime actualArrive;
    private final LocalDate dateOfTravel;
    private final int lateInMin;
    private final boolean nextDay;
    private final String flight;


    public Travel(LocalTime scheduledArrive, LocalTime actualArrive, LocalDate dateOfTravel, boolean nextDay, String flight) {
        this.scheduledArrive = scheduledArrive;
        this.actualArrive = actualArrive;
        this.dateOfTravel = dateOfTravel;
        this.nextDay = nextDay;
        this.flight = flight;
        this.lateInMin = lateCalculator(nextDay);
    }


    public int lateCalculator(boolean nextDay) {
        if (nextDay) {
            int scheduledDayLateSec = 86400 - scheduledArrive.toSecondOfDay();
            int actualDayArrive = actualArrive.toSecondOfDay();

            return (scheduledDayLateSec + actualDayArrive)/60;
        }
        return (int) ChronoUnit.MINUTES.between(scheduledArrive, actualArrive);
    }


    public LocalTime getScheduledArrive() {
        return scheduledArrive;
    }

    public LocalTime getActualArrive() {
        return actualArrive;
    }

    public LocalDate getDateOfTravel() {
        return dateOfTravel;
    }

    public int getLateInMin() {
        return lateInMin;
    }

    public boolean isNextDay() {
        return nextDay;
    }

    public String getFlight() {
        return flight;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(scheduledArrive)
                .append(" - ")
                .append(nextDay ? "(Másnap)" : "")
                .append(actualArrive)
                .append(" (").append(lateInMin).append(" min) ")
                .append(dateOfTravel.toString().replace("-", "."))
                .append(" Járat: ")
                .append(flight);
        return stringBuilder.toString();
    }
}
