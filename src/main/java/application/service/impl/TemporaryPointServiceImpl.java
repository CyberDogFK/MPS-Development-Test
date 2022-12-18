package application.service.impl;

import application.dao.TemporaryPointRepository;
import application.model.TemporaryPoint;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemporaryPointServiceImpl implements application.service.TemporaryPointService {
    private final TemporaryPointRepository temporaryPointRepository;

    public TemporaryPointServiceImpl(TemporaryPointRepository temporaryPointRepository) {
        this.temporaryPointRepository = temporaryPointRepository;
    }

    @Override
    public TemporaryPoint save(TemporaryPoint temporaryPoint) {
        return temporaryPointRepository.save(temporaryPoint);
    }

    @Override
    public List<TemporaryPoint> saveAll(List<TemporaryPoint> temporaryPoints) {
        return temporaryPointRepository.saveAll(temporaryPoints);
    }
}
