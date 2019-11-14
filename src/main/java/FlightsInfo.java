import java.io.Serializable;

public class FlightsInfo implements Serializable {

    private final int DELAYED = 18;
    private final int CANCELLED = 19;

    private float maxDelayedTime;
    private int numOfDelayed;
    private int numOfCanceled;
    private int numOfAll;

    FlightsInfo(String[] strings) {
        this.maxDelayedTime = Float.parseFloat(strings[DELAYED]);
        this.numOfDelayed = maxDelayedTime > 0.0 ? 1 : 0;
        this.numOfCanceled = ;
        this.numOfAll = numOfAll;
    }

    private int getDelay(String[] strings) {
        if (strings[DELAYED] !)
    }

    public float getMaxDelayedTime() {
        return maxDelayedTime;
    }

    public int getNumOfAll() {
        return numOfAll;
    }

    public int getNumOfCanceled() {
        return numOfCanceled;
    }

    public int getNumOfDelayed() {
        return numOfDelayed;
    }

}
