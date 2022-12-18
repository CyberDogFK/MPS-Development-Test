package application.dao;

import application.model.WayPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WayPointRepository extends JpaRepository<WayPoint, Long> {
}
