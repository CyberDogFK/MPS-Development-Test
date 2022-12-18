package application.service;

import application.model.TemporaryPoint;
import java.util.List;

public interface TemporaryPointService {
    TemporaryPoint save(TemporaryPoint temporaryPoint);

    List<TemporaryPoint> saveAll(List<TemporaryPoint> temporaryPoints);
}
