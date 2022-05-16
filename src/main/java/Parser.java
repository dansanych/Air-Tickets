import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String FILEPATH = "src/main/resources/tickets.json";
    private static final String TICKETS = "tickets";
    private static final String ORIGIN = "origin";
    private static final String ORIGIN_NAME = "origin_name";
    private static final String DESTINATION = "destination";
    private static final String DESTINATION_NAME = "destination_name";
    private static final String DEPARTURE_DATE = "departure_date";
    private static final String DEPARTURE_TIME = "departure_time";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String ARRIVAL_TIME = "arrival_time";
    private static final String CARRIER = "carrier";
    private static final String STOPS = "stops";
    private static final String PRICE = "price";

    public DataStorage parse() throws IOException, ParseException {
        DataStorage dataStorage = new DataStorage();

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILEPATH);

        JSONObject ticketJsonObject = (JSONObject) parser.parse(reader);

        JSONArray ticketJsonArray = (JSONArray) ticketJsonObject.get(TICKETS);

        List<Ticket> ticketList = new ArrayList<>();

        for (Object ticketObject : ticketJsonArray) {
            JSONObject dataStorageJsonObject = (JSONObject) ticketObject;

            String origin = (String) dataStorageJsonObject.get(ORIGIN);
            String originName = (String) dataStorageJsonObject.get(ORIGIN_NAME);
            String destination = (String) dataStorageJsonObject.get(DESTINATION);
            String destinationName = (String) dataStorageJsonObject.get(DESTINATION_NAME);
            String carrier = (String) dataStorageJsonObject.get(CARRIER);

            long stops = (Long) dataStorageJsonObject.get(STOPS);
            long price = (Long) dataStorageJsonObject.get(PRICE);

            DateTimeFormatter formatterForDates = DateTimeFormatter.ofPattern("dd.MM.yy");
            DateTimeFormatter formatterForTimes = DateTimeFormatter.ofPattern("H:mm");

            String departureDateString = (String) dataStorageJsonObject.get(DEPARTURE_DATE);
            LocalDate departureDate = LocalDate.parse(departureDateString, formatterForDates);
            String arrivalDateString = (String) dataStorageJsonObject.get(ARRIVAL_DATE);
            LocalDate arrivalDate = LocalDate.parse(arrivalDateString, formatterForDates);

            String departureTimeString = (String) dataStorageJsonObject.get(DEPARTURE_TIME);
            LocalTime departureTime = LocalTime.parse(departureTimeString, formatterForTimes);
            String arrivalTimeString = (String) dataStorageJsonObject.get(ARRIVAL_TIME);
            LocalTime arrivalTime = LocalTime.parse(arrivalTimeString, formatterForTimes);

            Ticket ticket = new Ticket(origin, originName, destination, destinationName, carrier, stops, price, departureDate, arrivalDate, departureTime, arrivalTime);
            ticketList.add(ticket);
        }

        dataStorage.setTickets(ticketList);

        return dataStorage;
    }
}
