package application.service.logical;

import application.model.AirplaneCharacteristics;
import application.model.TemporaryPoint;
import application.model.WayPoint;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PlaneCalculation {
    public List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics,
                                               List<WayPoint> wayPoints);
}
