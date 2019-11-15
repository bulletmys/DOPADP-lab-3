import java.io.Serializable;

public class FlightsInfo implements Serializable {

    private final int DELAYED = 18;
    private final int CANCELLED = 19;

    private float maxDelayedTime;
    private int numOfDelayed;
    private int numOfCanceled;
    private int numOfAll;

    FlightsInfo(String[] strings) {
        if (!strings[DELAYED].isEmpty() && !strings[CANCELLED].isEmpty()) {
            this.maxDelayedTime = Float.parseFloat(strings[DELAYED]);
            this.numOfDelayed = maxDelayedTime > 0.0 ? 1 : 0;
            this.numOfCanceled = Math.round(Float.parseFloat(strings[CANCELLED]));
            this.numOfAll = 1;
        }
    }

    private FlightsInfo(float maxDelayedTime, int numOfDelayed, int numOfCanceled, int numOfAll) {
        this.maxDelayedTime = maxDelayedTime;
        this.numOfDelayed = numOfDelayed;
        this.numOfCanceled = numOfCanceled;
        this.numOfAll = numOfAll;
    }

    public static FlightsInfo sum(FlightsInfo o1, FlightsInfo o2) {
        float maxDelayedTime = Math.max(o1.getMaxDelayedTime(), o2.getMaxDelayedTime());
        int numOfDelayed = o1.getNumOfDelayed() + o2.getNumOfDelayed();
        int numOfCanceled = o1.getNumOfCanceled() + o2.getNumOfCanceled();
        int numOfAll = o1.getNumOfAll() + o2.getNumOfAll();
        return new FlightsInfo(maxDelayedTime, numOfDelayed, numOfCanceled, numOfAll);
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

    @Override
    public String toString() {
        return "Max Delay Time: " + maxDelayedTime + " Delayed % : " + ((float) numOfDelayed / numOfAll * 100f) + " Canceled % : " + ((float) numOfCanceled / numOfAll * 100f);
    }
}
