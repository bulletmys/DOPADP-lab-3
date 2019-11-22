import java.io.Serializable;
import java.util.ArrayList;

public class FlightsAdditionalInfo implements Serializable {

    private int[] canceled = new int[7];

    FlightsAdditionalInfo(Integer dayOfWeek, Integer numOfCanceled) {
        this.canceled[dayOfWeek] = numOfCanceled;
    }

    public static FlightsAdditionalInfo sum(FlightsInfo o1, FlightsInfo o2) {
        float maxDelayedTime = Math.max(o1.getMaxDelayedTime(), o2.getMaxDelayedTime());
        int numOfDelayed = o1.getNumOfDelayed() + o2.getNumOfDelayed();
        int numOfCanceled = o1.getNumOfCanceled() + o2.getNumOfCanceled();
        int numOfAll = o1.getNumOfAll() + o2.getNumOfAll();
        return new FlightsAdditionalInfo(maxDelayedTime, numOfDelayed, numOfCanceled, numOfAll);
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
