import org.apache.spark.api.java.JavaRDD;

public class FlightsParser {
    private JavaRDD<String[]> strings;

    FlightsParser(JavaRDD<String> stringJavaRDD) {
        stringJavaRDD.map(s -> s.replaceAll("\"", "").split(","));
    }

    public JavaRDD<String[]> getStrings() {
        return strings;
    }
}
