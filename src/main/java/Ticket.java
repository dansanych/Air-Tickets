import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private String carrier;

    private long stops;
    private long price;

    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public Ticket(String origin, String originName, String destination, String destinationName, String carrier, long stops, long price, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public long getStops() {
        return stops;
    }

    public void setStops(long stops) {
        this.stops = stops;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
