package application.dao;

import application.model.AirplaneCharacteristics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneCharacteristicsRepository extends JpaRepository<AirplaneCharacteristics, Long> {
}
