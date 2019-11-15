import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class Spark {
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;

    private static String getOriginAirportID(String[] string) {
        return string[ORIGIN_AIRPORT_ID];
    }

    private static String getDestAirportID(String[] string) {
        return string[DEST_AIRPORT_ID];
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("START");
        JavaRDD<String> flightsInfo = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportsNames = sc.textFile("L_AIRPORT_ID.csv");

        FlightsParser flightsParser = new FlightsParser(flightsInfo);

        JavaRDD<String[]> parsedFlightsInfo = flightsParser.getStrings().filter(strings -> !strings[0].equals("YEAR"));

        JavaPairRDD<Tuple2<String, String>, FlightsInfo> data = parsedFlightsInfo
                .mapToPair(i ->
                        new Tuple2<Tuple2<String, String>, FlightsInfo>(
                                new Tuple2<String, String>(
                                        getOriginAirportID(i), getDestAirportID(i)), new FlightsInfo(i)));

        JavaPairRDD<Tuple2<String, String>, FlightsInfo> flightsStat = data.reduceByKey(FlightsInfo::sum);

        FlightsParser airportsNamesParser = new FlightsParser(airportsNames);
        JavaRDD<String[]> parsedAirportsInfo = airportsNamesParser.getStrings().filter(strings -> !strings[0].equals("Code"));

        JavaPairRDD<Integer, String> airportIdNamePairs = parsedAirportsInfo
                .mapToPair(strings ->
                        new Tuple2<Integer, String>(Integer.parseInt(strings[0]), strings.length == 3 ? strings[2] : strings[1]));

        Map<Integer, String> airportIdNameMap = airportIdNamePairs.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportIdNameMap);

        flightsStat.map()



    }
}
