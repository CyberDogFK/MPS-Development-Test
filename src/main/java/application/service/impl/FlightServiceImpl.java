package application.service.impl;

import application.dao.FlightRepository;
import application.model.Flight;
import application.model.TemporaryPoint;
import application.model.WayPoint;
import application.service.FlightService;
import application.service.TemporaryPointService;
import application.service.WayPointService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private WayPointService wayPointService;
    private TemporaryPointService temporaryPointService;

    public FlightServiceImpl(FlightRepository flightRepository, WayPointService wayPointService,
                             TemporaryPointService temporaryPointService) {
        this.flightRepository = flightRepository;
        this.wayPointService = wayPointService;
        this.temporaryPointService = temporaryPointService;
    }

    @Override
    public Flight save(Flight flight) {
        List<WayPoint> wayPoints = wayPointService.saveAll(flight.getWayPoints());
        flight.setWayPoints(wayPoints);
        List<TemporaryPoint> temporaryPoints = temporaryPointService.saveAll(flight.getTemporaryPoints());
        flight.setTemporaryPoints(temporaryPoints);
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }
}
