package tm.kesesek;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Stack;

public class TravelsStack implements Serializable {


    private final Stack<Travel> travelsStack;
    private int greatestLate = 0;
    private LocalDate greatestLateDate = null;
    private int arrivalDifferences = 0;
    private int onlyLateTotal = 0;
    private int onlyLateMin = 0;


    public TravelsStack() {
        this.travelsStack = new Stack<>();
    }


    public void addNewTravel(Travel travel) {
        travelsStack.push(travel);
        int newLateMin = travel.getLateInMin();
        arrivalDifferences += newLateMin;

        if (newLateMin > 0) {
            onlyLateTotal++;
            onlyLateMin += newLateMin;
        }
        updateInfo();
    }

    public void removeLastTravel() {
        if (travelsStack.empty()) return;

        int removedLateMin = travelsStack.pop().getLateInMin();
        arrivalDifferences -= removedLateMin;

        if (removedLateMin > 0) {
            onlyLateTotal--;
            onlyLateMin -= removedLateMin;
        }
        updateInfo();
    }

    private void updateInfo() {
        greatestLate = 0;
        greatestLateDate = null;

        if (travelsStack.empty()) return;

        for (Travel travel : travelsStack) {
            if (greatestLate < travel.getLateInMin()) {
                greatestLate = travel.getLateInMin();
                greatestLateDate = travel.getDateOfTravel();
            }
        }
    }



    public int getGreatestLate() {
        return greatestLate;
    }

    public String getGreatestLateDate() {
        return greatestLateDate.toString().replace("-", ".");
    }

    public int getArrivalDifferences() {
        return arrivalDifferences;
    }

    public int getNumberOfTravels() {
        return travelsStack.size();
    }

    public int getOnlyLateTotal() {
        return onlyLateTotal;
    }

    public int getOnlyLateMin() {
        return onlyLateMin;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(travelsStack.empty())
            return "Még nem vettél fel utazást";

        for(int i = travelsStack.size(); i > 0; i--)
            stringBuilder.append(i).append(".\t").append(travelsStack.get(i-1).toString()).append("\n");

        return stringBuilder.toString();
    }

}
