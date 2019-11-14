import java.io.Serializable;

public class FlightsInfo implements Serializable {

    private final int DELAYED = 18

    private float maxDelayedTime;
    private int numOfDelayed;
    private int numOfCanceled;
    private int numOfAll;

    FlightsInfo(String[] strings) {
        this.maxDelayedTime = Float.parseFloat(strings[18]);
        this.numOfDelayed =  getDelay();
        this.numOfCanceled = numOfCanceled;
        this.numOfAll = numOfAll;
    }

    private int getDelay() {

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
