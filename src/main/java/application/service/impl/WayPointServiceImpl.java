package application.service.impl;

import application.dao.WayPointRepository;
import application.model.WayPoint;
import application.service.WayPointService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WayPointServiceImpl implements WayPointService {
    private WayPointRepository wayPointRepository;

    public WayPointServiceImpl(WayPointRepository wayPointRepository) {
        this.wayPointRepository = wayPointRepository;
    }

    @Override
    public WayPoint save(WayPoint wayPoint) {
        return wayPointRepository.save(wayPoint);
    }

    @Override
    public List<WayPoint> saveAll(List<WayPoint> wayPoints) {
        return wayPointRepository.saveAll(wayPoints);
    }
}
