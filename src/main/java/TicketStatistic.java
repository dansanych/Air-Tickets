import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TicketStatistic {

    private static final String DEPARTURECITY_DEFAULT = "Владивосток";
    private static final String ARRIVALCITY_DEFAULT = "Тель-Авив";
    private static final int PERCENTILE_DEFAULT = 90;

    public static void main(String[] args) throws IOException, ParseException {
        Parser parser = new Parser();

        DataStorage dataStorage = parser.parse();

        String departureCity = DEPARTURECITY_DEFAULT;
        String arrivalCity = ARRIVALCITY_DEFAULT;
        int percentile = PERCENTILE_DEFAULT;

        if (args.length == 3) {
            departureCity = args[0];
            arrivalCity = args[1];
            int p = Integer.parseInt(args[2]);
            if (p > 0 && p < 100) {
                percentile = p;
            }
        }

        long averageMinutes = getAverageTimeBetweenCities(departureCity, arrivalCity, dataStorage)
                .toMinutes();
        long percentileMinutes = getFlightTimePercentileBetweenCities(dataStorage, percentile, departureCity, arrivalCity)
                .toMinutes();

        System.out.printf("Среднее время полета между городами %s и %s составляет: %d ч %d мин\n", departureCity, arrivalCity, averageMinutes / 60, averageMinutes % 60);
        System.out.printf("%d-й процентиль времени полета между городами %s и %s составляет: %d ч %d мин\n", percentile, departureCity, arrivalCity, percentileMinutes / 60, percentileMinutes % 60);
    }

    public static Duration getAverageTimeBetweenCities(String departureCity, String arrivalCity, DataStorage dataStorage) {
        Duration average = Duration.ZERO;
        int k = 0;

        for (Ticket t : dataStorage.getTickets()) {
            if (t.getOriginName().equals(departureCity) && t.getDestinationName().equals(arrivalCity)) {
                average = average.plus(calculateFlightTime(t));
                k++;
            }
        }
        if (k == 0) {
            throw new IllegalArgumentException("cities name error");
        }

        return average.dividedBy(k);
    }

    public static Duration calculateFlightTime(Ticket ticket) {
        LocalDateTime departure = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
        LocalDateTime arrival = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());

        return Duration.between(departure, arrival);
    }

    public static Duration getFlightTimePercentileBetweenCities(DataStorage dataStorage, int percentile, String
            departureCity, String arrivalCity) {
        List<Ticket> ticketsByFlightTime = dataStorage.getTickets()
                .stream()
                .filter(ticket -> ticket.getOriginName().equals(departureCity))
                .filter(ticket -> ticket.getDestinationName().equals(arrivalCity))
                .distinct()
                .sorted((o1, o2) -> (int) (calculateFlightTime(o1).toMinutes() - calculateFlightTime(o2).toMinutes()))
                .collect(Collectors.toList());

        double coefficient = (double) percentile / 100 * ticketsByFlightTime.size();
        int index = (int) Math.ceil(coefficient) - 1;

        return calculateFlightTime(ticketsByFlightTime.get(index));
    }
}