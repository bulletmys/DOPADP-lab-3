import java.io.Serializable;
import java.util.ArrayList;

public class FlightsAdditionalInfo implements Serializable {

    private int[] canceled = new int[8];

    FlightsAdditionalInfo(Integer dayOfWeek, Integer numOfCanceled) {
        this.canceled[dayOfWeek] = numOfCanceled;
    }
    
    public int getNumOfCanceled(int dayOfWeek) {
        if (dayOfWeek > 0)
        return canceled[dayOfWeek];
    }


    @Override
    public String toString() {
        return "Day Of Week: " + dayOfWeek + " Canceled: " + numOfCanceled;
    }
}
