package application.service;

import application.model.Flight;
import java.util.List;

public interface FlightService {
    Flight save(Flight flight);

    List<Flight> getAll();
}
