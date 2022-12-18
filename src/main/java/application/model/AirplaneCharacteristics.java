package application.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class AirplaneCharacteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double maxSpeed;
    private Double maxAcceleration;
    private Double maxChangeOfAltitude;
    private Double maxCourseChange;
}
