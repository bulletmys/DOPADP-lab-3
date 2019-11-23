import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class SparkAdditional { //Требуется составить для каждого аэропорта кол-во отмененных рейсов по дням неделей
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int DAY_OF_WEEK = 4;
    private static final int CANCELLED = 19;
    private static final int CANCELLED_ALT = 15;

    private static Integer getOriginAirportID(String[] string) {
        return Integer.parseInt(string[ORIGIN_AIRPORT_ID]);
    }

    private static Integer getDayOfWeek(String[] string) {
        return Integer.parseInt(string[DAY_OF_WEEK]);
    }

    private static Integer getNumOfCanceled(String[] string) {
        int res = Math.round(Float.parseFloat(string[CANCELLED]));
        return res == 0 ? 0 : Math.round(Float.parseFloat(string[CANCELLED_ALT]));
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("START");
        JavaRDD<String> flightsInfo = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportsNames = sc.textFile("L_AIRPORT_ID.csv");

        FlightsParser flightsParser = new FlightsParser(flightsInfo);
        JavaRDD<String[]> parsedFlightsInfo = flightsParser.getStrings().filter(strings -> !strings[0].equals("YEAR"));

        parsedFlightsInfo.saveAsTextFile("Three");

        JavaPairRDD<Tuple2<Integer, Integer>, Integer> data = parsedFlightsInfo.mapToPair(i ->
                new Tuple2<>(
                        new Tuple2<>(getOriginAirportID(i), getDayOfWeek(i)), getNumOfCanceled(i)));

        JavaPairRDD<Tuple2<Integer, Integer>, Integer> flightsCanceledCalc = data.reduceByKey(Integer::sum);
        JavaPairRDD<Integer, FlightsAdditionalInfo> canceledStats = flightsCanceledCalc.mapToPair(i ->
                new Tuple2<>(i._1._1,
                        new FlightsAdditionalInfo(i._1._2, i._2)));

        JavaPairRDD<Integer, FlightsAdditionalInfo> canceledRes = canceledStats.reduceByKey(FlightsAdditionalInfo::sum);

        FlightsParser airportsNamesParser = new FlightsParser(airportsNames);
        JavaRDD<String[]> parsedAirportsInfo = airportsNamesParser.getStrings().filter(strings -> !strings[0].equals("Code") && !strings[1].isEmpty());

        JavaPairRDD<Integer, String> airportIdNamePairs = parsedAirportsInfo
                .mapToPair(strings ->
                        new Tuple2<Integer, String>(Integer.parseInt(strings[0]), strings.length == 3 ? strings[2] : strings[1]));

        Map<Integer, String> airportIdNameMap = airportIdNamePairs.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportIdNameMap);

        canceledRes.map(x -> airportsBroadcasted.value().get(x._1) + " CancelledByDayOfWeek: " + x._2.toString()).saveAsTextFile("Two");


    }
}
