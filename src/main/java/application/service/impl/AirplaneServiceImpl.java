package application.service.impl;

import application.dao.AirplaneRepository;
import application.model.Airplane;
import application.model.Flight;
import application.service.AirplaneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public Airplane getById(Long id) {
        return airplaneRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find airplane with id " + id));
    }

    @Override
    public List<Flight> getAllFlights(Long id) {
        return airplaneRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find airplane with id " + id)).getFlights();
    }
}
