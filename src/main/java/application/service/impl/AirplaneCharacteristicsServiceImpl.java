package application.service.impl;

import application.dao.AirplaneCharacteristicsRepository;
import application.model.AirplaneCharacteristics;
import application.service.AirplaneCharacteristicsService;
import org.springframework.stereotype.Service;

@Service
public class AirplaneCharacteristicsServiceImpl implements AirplaneCharacteristicsService {
    private AirplaneCharacteristicsRepository airplaneCharacteristicsRepository;

    public AirplaneCharacteristicsServiceImpl(AirplaneCharacteristicsRepository airplaneCharacteristicsRepository) {
        this.airplaneCharacteristicsRepository = airplaneCharacteristicsRepository;
    }

    @Override
    public AirplaneCharacteristics save(AirplaneCharacteristics airplaneCharacteristics) {
        return airplaneCharacteristicsRepository.save(airplaneCharacteristics);
    }
}
