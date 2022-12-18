package application.service;

import application.model.Airplane;
import application.model.Flight;

import java.util.List;

public interface AirplaneService {
    Airplane save(Airplane airplane);

    Airplane getById(Long id);

    List<Flight> getAllFlights(Long id);


}
