package application.service;

import application.model.WayPoint;
import java.util.List;

public interface WayPointService {
    WayPoint save(WayPoint wayPoint);

    List<WayPoint> saveAll(List<WayPoint> wayPoints);
}
