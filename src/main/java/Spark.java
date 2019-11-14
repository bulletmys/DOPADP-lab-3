import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Spark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("START");
        JavaRDD<String> flightsInfo = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportsNames = sc.textFile("L_AIRPORT_ID.csv");

        FlightsParser flightsParser = new FlightsParser(flightsInfo);

        JavaRDD<String[]> parsedFlightsInfo = flightsParser.getStrings();

        JavaPairRDD<Tuple2<String, String>, FlightsInfo> data = parsedFlightsInfo
                .mapToPair(i ->
                        new Tuple2<Tuple2<String, String>, FlightsInfo>())


    }
}
