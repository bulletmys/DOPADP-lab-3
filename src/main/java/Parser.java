import org.apache.spark.api.java.JavaRDD;

public class Parser {
    private JavaRDD<String[]> strings;

    Parser(JavaRDD<String> stringJavaRDD) {
        stringJavaRDD.map(s -> s.replaceAll("\"", "").split(","));
    }

    public JavaRDD<String[]> getStrings() {
        return strings;
    }
}
