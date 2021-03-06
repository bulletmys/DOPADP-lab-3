import java.io.Serializable;
import java.util.ArrayList;

public class FlightsAdditionalInfo implements Serializable {

    private int[] canceled = new int[8];

    FlightsAdditionalInfo(Integer dayOfWeek, Integer numOfCanceled) {
        if (dayOfWeek > 0 && dayOfWeek < 8)
            this.canceled[dayOfWeek] = numOfCanceled;
    }

    static FlightsAdditionalInfo sum(FlightsAdditionalInfo o1, FlightsAdditionalInfo o2) {
        FlightsAdditionalInfo resObj = new FlightsAdditionalInfo(0, 0);
        for (int i = 1; i < 8; ++i) {
            resObj.canceled[i] = o1.canceled[i] + o2.canceled[i];
        }
        return resObj;
    }

    @Override
    public String toString() {
        return  "Mon - " + canceled[1] +
                " Tue - " + canceled[2] +
                " Wed - " + canceled[3] +
                " Thu - " + canceled[4] +
                " Fri - " + canceled[5] +
                " Sat - " + canceled[6] +
                " Sun - " + canceled[7];
    }
}
