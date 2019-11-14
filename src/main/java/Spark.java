import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Spark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("KEKLOL ARBIDOL");
        JavaRDD<String> flights = sc.textFile("Flights.csv");

        
        JavaRDD<String[]> flights_lines = flights.map(s -> s.replaceAll("\"", "").split(",")).filter(strings -> !strings[0].equals("Flights"));
        System.out.println(flights_lines);
    }
}
