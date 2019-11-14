import java.io.Serializable;

public class FlightsInfo implements Serializable {
    private float maxDelayedTime;
    private int numOfDelayed;
    private int numOfCanceled;
    private int numOfAll;

    FlightsInfo(float maxDelayedTime, int numOfDelayed, int numOfCanceled, int numOfAll) {
        this.maxDelayedTime = maxDelayedTime;
        this.numOfDelayed = numOfDelayed;
        this.numOfCanceled = numOfCanceled;
        this.numOfAll = numOfAll;
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
