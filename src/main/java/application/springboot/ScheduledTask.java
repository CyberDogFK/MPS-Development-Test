package application.springboot;

import application.model.Airplane;
import application.model.AirplaneCharacteristics;
import application.model.Flight;
import application.model.TemporaryPoint;
import application.model.WayPoint;
import application.service.AirplaneCharacteristicsService;
import application.service.AirplaneService;
import application.service.FlightService;
import application.service.TemporaryPointService;
import application.service.logical.PlaneCalculation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ComponentScan("application")
public class ScheduledTask {
    private final PlaneCalculation planeCalculation;
    private final AirplaneCharacteristicsService airplaneCharacteristicsService;
    private final AirplaneService airplaneService;
    private final FlightService flightService;
    private final TemporaryPointService temporaryPointService;

    public ScheduledTask(PlaneCalculation planeCalculation,
                         AirplaneCharacteristicsService airplaneCharacteristicsService,
                         AirplaneService airplaneService, FlightService flightService,
                         TemporaryPointService temporaryPointService) {
        this.planeCalculation = planeCalculation;
        this.airplaneCharacteristicsService = airplaneCharacteristicsService;
        this.airplaneService = airplaneService;
        this.flightService = flightService;
        this.temporaryPointService = temporaryPointService;
    }

    public void doTask1() {
        AirplaneCharacteristics airplaneCharacteristics = prepareAirplaneCharacteristics(20.0, 5.0,
                5.0, 3.0);
        TemporaryPoint startingTemporaryPoint =
                prepareTemporaryPoint(0.0, 0.0, 0.0, 0.0, 0.0);
        startingTemporaryPoint = temporaryPointService.save(startingTemporaryPoint);
        Airplane airplane = prepareAirplane(List.of(),
                startingTemporaryPoint,
                airplaneCharacteristics);
        WayPoint wayPoint1 = prepareWayPoint(10.0, 10.0, 10.0, 10.0);
        WayPoint wayPoint2 = prepareWayPoint(20.0, 20.0, 20.0, 10.0);
        WayPoint wayPoint3 = prepareWayPoint(30.0, 30.0, 30.0, 10.0);

        List<WayPoint> wayPoints = List.of(wayPoint1, wayPoint2, wayPoint3);
        List<TemporaryPoint> temporaryPoints = planeCalculation.calculateRoute(airplaneCharacteristics, wayPoints);
        Flight flight = prepareFlight(wayPoints, temporaryPoints);
        saveResultOfTask(flight, airplaneCharacteristics, airplane);
    }

    public void doTask2() {
        AirplaneCharacteristics airplaneCharacteristics = prepareAirplaneCharacteristics(25.0, 7.0,
                10.0, 20.0);
        TemporaryPoint startingTemporaryPoint =
                prepareTemporaryPoint(0.0, 0.0, 0.0, 0.0, 0.0);
        startingTemporaryPoint = temporaryPointService.save(startingTemporaryPoint);
        Airplane airplane = prepareAirplane(List.of(),
                startingTemporaryPoint,
                airplaneCharacteristics);
        WayPoint wayPoint1 = prepareWayPoint(30.0, 30.0, 10.0, 20.0);
        WayPoint wayPoint2 = prepareWayPoint(10.0, 10.0, 50.0, 10.0);
        WayPoint wayPoint3 = prepareWayPoint(30.0, 30.0, 30.0, 5.0);

        List<WayPoint> wayPoints = List.of(wayPoint1, wayPoint2, wayPoint3);
        List<TemporaryPoint> temporaryPoints = planeCalculation.calculateRoute(airplaneCharacteristics, wayPoints);
        Flight flight = prepareFlight(wayPoints, temporaryPoints);
        airplane.setFlights(List.of(flight));
        saveResultOfTask(flight, airplaneCharacteristics, airplane);
    }

    public void doTask3() {
        AirplaneCharacteristics airplaneCharacteristics = prepareAirplaneCharacteristics(100.0, 10.0,
                10.0, 20.0);
        TemporaryPoint startingTemporaryPoint =
                prepareTemporaryPoint(0.0, 0.0, 0.0, 0.0, 0.0);
        startingTemporaryPoint = temporaryPointService.save(startingTemporaryPoint);
        Airplane airplane = prepareAirplane(List.of(),
                startingTemporaryPoint,
                airplaneCharacteristics);
        WayPoint wayPoint1 = prepareWayPoint(40.0, 40.0, 40.0, 40.0);
        WayPoint wayPoint2 = prepareWayPoint(1.0, 50.0, 100.0, 70.0);
        WayPoint wayPoint3 = prepareWayPoint(60.0, 80.0, 30.0, 5.0);

        List<WayPoint> wayPoints = List.of(wayPoint1, wayPoint2, wayPoint3);
        List<TemporaryPoint> temporaryPoints = planeCalculation.calculateRoute(airplaneCharacteristics, wayPoints);
        Flight flight = prepareFlight(wayPoints, temporaryPoints);
        airplane.setFlights(List.of(flight));
        saveResultOfTask(flight, airplaneCharacteristics, airplane);
    }

    private void saveResultOfTask(Flight flight, AirplaneCharacteristics airplaneCharacteristics,
                                  Airplane airplane) {
        flightService.save(flight);
        airplaneCharacteristicsService.save(airplaneCharacteristics);
        airplaneService.save(airplane);
    }

    private Flight prepareFlight(List<WayPoint> wayPoints, List<TemporaryPoint> temporaryPoints) {
        Flight flight = new Flight();

        flight.setWayPoints(wayPoints);
        flight.setTemporaryPoints(temporaryPoints);
        return flight;
    }


    private Airplane prepareAirplane(List<Flight> flights, TemporaryPoint position,
                                     AirplaneCharacteristics airplaneCharacteristics) {
        Airplane airplane = new Airplane();
        airplane.setFlights(flights);
        airplane.setPosition(position);
        airplane.setAirplaneCharacteristics(airplaneCharacteristics);
        return airplane;
    }

    private TemporaryPoint prepareTemporaryPoint(Double latitude, Double longitude,
                                          Double flightHeight, Double flightSpeed,
                                          Double course) {
        TemporaryPoint temporaryPoint = new TemporaryPoint();
        temporaryPoint.setLatitude(latitude);
        temporaryPoint.setLongitude(longitude);
        temporaryPoint.setFlightHeight(flightHeight);
        temporaryPoint.setFlightSpeed(flightSpeed);
        temporaryPoint.setCourse(course);
        return temporaryPoint;
    }

    private WayPoint prepareWayPoint(Double latitude, Double longitude,
                                     Double flightHeight, Double flightSpeed) {
        WayPoint wayPoint = new WayPoint();
        wayPoint.setLatitude(latitude);
        wayPoint.setLongitude(longitude);
        wayPoint.setFlightHeight(flightHeight);
        wayPoint.setFlightSpeed(flightSpeed);
        return wayPoint;
    }

    private AirplaneCharacteristics prepareAirplaneCharacteristics(Double maxSpeed, Double maxAcceleration,
                                                                   Double maxChangeOfAltitude, Double maxCourseChange) {
        AirplaneCharacteristics airplaneCharacteristics = new AirplaneCharacteristics();
        airplaneCharacteristics.setMaxSpeed(maxSpeed);
        airplaneCharacteristics.setMaxAcceleration(maxAcceleration);
        airplaneCharacteristics.setMaxChangeOfAltitude(maxChangeOfAltitude);
        airplaneCharacteristics.setMaxCourseChange(maxCourseChange);
        return airplaneCharacteristics;
    }
}
