import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Spark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("START");
        JavaRDD<String> flights = sc.textFile("Flights.csv");
        JavaRDD<String> flights = sc.textFile("Flights.csv");

        System.out.println(flights_lines);
    }
}
