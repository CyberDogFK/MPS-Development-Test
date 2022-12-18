package application.dao;

import application.model.TemporaryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryPointRepository extends JpaRepository<TemporaryPoint, Long> {
}
