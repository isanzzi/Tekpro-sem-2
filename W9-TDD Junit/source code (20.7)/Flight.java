import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public abstract class Flight {
    private String id;
    List<Passenger> passengers = new ArrayList<Passenger>();
    public Flight(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public List<Passenger> getPassengersList() {
        return Collections.unmodifiableList(passengers);
    }
    public abstract boolean addPassenger(Passenger passenger);
    public abstract boolean removePassenger(Passenger passenger);
}