package application.service.logical.impl;

import application.model.AirplaneCharacteristics;
import application.model.TemporaryPoint;
import application.model.WayPoint;
import application.service.logical.PlaneCalculation;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneCalculationImpl implements PlaneCalculation {
    public List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics,
                                               List<WayPoint> wayPoints) {
        List<TemporaryPoint> temporaryPoints = new ArrayList<>();
        TemporaryPoint currentTemporaryPoint = startTemporaryPointToNorth();

        System.out.println(wayPoints);
        temporaryPoints.add(currentTemporaryPoint);
        long counter = 0;
        for (WayPoint wayPoint : wayPoints) {
            System.out.println("Way point " + wayPoint);
            while (isNextWayPointPassed(currentTemporaryPoint, wayPoint) && counter < 150) {
                TemporaryPoint nextTemporaryPoint = calculateNextTemporaryPoint(currentTemporaryPoint,
                        wayPoint, characteristics);
                temporaryPoints.add(nextTemporaryPoint);
                currentTemporaryPoint = nextTemporaryPoint;
                currentTemporaryPoint.setId(counter++);
                System.out.println("way point" + wayPoint);
                System.out.println("current waypoint " + currentTemporaryPoint);
                temporaryPoints.add(currentTemporaryPoint);
            }
        }
        return temporaryPoints;
    }

    private boolean isNextWayPointPassed(TemporaryPoint currentTemporaryPoint, WayPoint wayPoint) {
        return isLongitudeCross(currentTemporaryPoint.getLongitude(), wayPoint.getLongitude())
                && isLatitudeCross(currentTemporaryPoint.getLatitude(), wayPoint.getLatitude());
    }

    private boolean isLatitudeCross(Double currentLatitude, Double wayPointLatitude) {
        if (wayPointLatitude > 0) {
            return currentLatitude < wayPointLatitude;
        }
        if (wayPointLatitude < 0) {
            return currentLatitude > wayPointLatitude;
        }
        return false;
    }

    private boolean isLongitudeCross(Double currentLongitude, Double wayPointLongitude) {
        if (wayPointLongitude > 0) {
            return currentLongitude < wayPointLongitude;
        }
        if (wayPointLongitude < 0) {
            return currentLongitude > wayPointLongitude;
        }
        return false;
    }

    private TemporaryPoint calculateNextTemporaryPoint(TemporaryPoint temporaryPoint,
                                                       WayPoint nextWayPoint,
                                                       AirplaneCharacteristics characteristics) {
        TemporaryPoint newTemporaryPoint = new TemporaryPoint();
        newTemporaryPoint.setFlightSpeed(calculateSpeed(temporaryPoint, characteristics));
        newTemporaryPoint.setFlightHeight(calculateHeight(temporaryPoint, nextWayPoint, characteristics));
        newTemporaryPoint.setCourse(calculateCourse(temporaryPoint, nextWayPoint, characteristics));
        newTemporaryPoint.setLatitude(calculateLatitude(temporaryPoint, nextWayPoint));
        newTemporaryPoint.setLongitude(calculateLongitude(temporaryPoint, nextWayPoint));
        return newTemporaryPoint;
    }

    private Double calculateLongitude(TemporaryPoint temporaryPoint, WayPoint wayPoint) {
        double longitudeDifferance = 0.0;
        if (temporaryPoint.getLatitude() < wayPoint.getLatitude()) {
            // верхня частина кординатної сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в правому верхньому кутку
                longitudeDifferance = temporaryPoint.getFlightSpeed() * Math.abs(Math.sin(temporaryPoint.getCourse()));
                System.out.println("longitude " + Math.sin(temporaryPoint.getFlightSpeed() / temporaryPoint.getCourse()));
                System.out.println("longitude dif:" + longitudeDifferance);
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в лівому верхньому кутку
                longitudeDifferance = temporaryPoint.getFlightSpeed() * Math.abs(Math.sin(temporaryPoint.getCourse() - 270));
            }
            return temporaryPoint.getLongitude() + longitudeDifferance;
        }
        if (temporaryPoint.getLatitude() > wayPoint.getLatitude()) {
            // нижня частина кординат сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в нижньому правому кутку
                longitudeDifferance = temporaryPoint.getFlightSpeed() * Math.abs(Math.sin(temporaryPoint.getCourse() - 90));
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в нижньому лівому кутку
                longitudeDifferance = temporaryPoint.getFlightSpeed() * Math.abs(Math.sin(temporaryPoint.getCourse() - 180));
            }
            return temporaryPoint.getLongitude() - longitudeDifferance;
        }

        return temporaryPoint.getLatitude();
    }

    private Double calculateLatitude(TemporaryPoint temporaryPoint, WayPoint wayPoint) {
        double latitudeDifference = 0.0;
        if (temporaryPoint.getLatitude() < wayPoint.getLatitude()) {
            // верхня частина кординатної сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в правому верхньому кутку
                latitudeDifference = temporaryPoint.getFlightSpeed() * Math.abs(Math.cos(temporaryPoint.getCourse()));
                System.out.println("latitude dif:" + latitudeDifference);
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в лівому верхньому кутку
                latitudeDifference = temporaryPoint.getFlightSpeed() * Math.abs(Math.cos(temporaryPoint.getCourse() - 270));
            }
            return temporaryPoint.getLatitude() + latitudeDifference;
        }
        if (temporaryPoint.getLatitude() > wayPoint.getLatitude()) {
            // нижня частина кординат сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в нижньому правому кутку
                latitudeDifference = temporaryPoint.getFlightSpeed() * Math.abs(Math.cos(temporaryPoint.getCourse() - 90));
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в нижньому лівому кутку
                latitudeDifference = temporaryPoint.getFlightSpeed() * Math.abs(Math.cos(temporaryPoint.getCourse() - 180));
            }
            return temporaryPoint.getLatitude() - latitudeDifference;
        }
        return temporaryPoint.getLatitude();
    }

    private Double calculateHeight(TemporaryPoint temporaryPoint, WayPoint wayPoint,
                                   AirplaneCharacteristics characteristics) {
        double newHeight = temporaryPoint.getFlightHeight();
        if (wayPoint.getFlightHeight() > temporaryPoint.getFlightHeight()) {
            newHeight += characteristics.getMaxChangeOfAltitude();
        }
        if (wayPoint.getFlightHeight() < temporaryPoint.getFlightHeight()) {
            newHeight -= characteristics.getMaxChangeOfAltitude();
        }
        return newHeight;
    }

    private Double calculateCourse(TemporaryPoint temporaryPoint, WayPoint wayPoint,
                                   AirplaneCharacteristics characteristics) {
        double currentAngle = temporaryPoint.getCourse();
        double angleToThePoint = getAngleToThePoint(temporaryPoint, wayPoint);
        System.out.println("angle " + angleToThePoint);
        if (angleToThePoint < currentAngle) {
            currentAngle -= characteristics.getMaxCourseChange();
        }
        if (angleToThePoint > currentAngle) {
            currentAngle += characteristics.getMaxCourseChange();
        }
        return currentAngle;
    }

    private Double getAngleToThePoint(TemporaryPoint temporaryPoint, WayPoint wayPoint) {
        double angleToThePoint = temporaryPoint.getCourse();
        if (temporaryPoint.getLatitude() < wayPoint.getLatitude()) {
            // верхня частина кординатної сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в правому верхньому кутку
                double catetA = wayPoint.getLongitude() - temporaryPoint.getLongitude();
                double catetB = wayPoint.getLatitude() - temporaryPoint.getLatitude();
                angleToThePoint = Math.atan2(catetA, catetB);
                System.out.println("more than");
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в лівому верхньому кутку
                double catetA = temporaryPoint.getLatitude() - wayPoint.getLatitude();
                double catetB = wayPoint.getLongitude() - temporaryPoint.getLongitude();
                angleToThePoint = Math.atan2(catetA, catetB) + 270;
            }
            System.out.println(temporaryPoint.getFlightSpeed());
            System.out.println(temporaryPoint.getCourse());
            System.out.println("angle " + angleToThePoint);
            System.out.println(temporaryPoint.getLongitude());
        }
        if (temporaryPoint.getLatitude() > wayPoint.getLatitude()) {
            // нижня частина кординат сітки
            if (temporaryPoint.getLongitude() < wayPoint.getLongitude()) {
                // точка в нижньому правому кутку
                double catetA = temporaryPoint.getLongitude() - wayPoint.getLongitude();
                double catetB = wayPoint.getLatitude() - temporaryPoint.getLatitude();
                angleToThePoint = Math.atan2(catetA, catetB) + 90;
            }
            if (temporaryPoint.getLongitude() > wayPoint.getLongitude()) {
                // точка в нижньому лівому кутку
                double catetA = temporaryPoint.getLongitude() - wayPoint.getLongitude();
                double catetB = temporaryPoint.getLatitude() - wayPoint.getLatitude();
                angleToThePoint = Math.atan2(catetA, catetB) + 180;
            }
        }
        if (angleToThePoint > 360) {
            angleToThePoint -= 360;
        }
        return angleToThePoint;
    }

    private Double calculateSpeed(TemporaryPoint temporaryPoint,
                                  AirplaneCharacteristics characteristics) {
        Double speed = temporaryPoint.getFlightSpeed() + characteristics.getMaxAcceleration();
        if (speed > characteristics.getMaxSpeed()) {
            speed = characteristics.getMaxSpeed();
        }
        return speed;
    }

    private TemporaryPoint startTemporaryPointToNorth() {
        TemporaryPoint temporaryPoint = new TemporaryPoint();
        temporaryPoint.setLatitude(0.0);
        temporaryPoint.setLongitude(0.0);
        temporaryPoint.setFlightHeight(0.0);
        temporaryPoint.setFlightSpeed(0.0);
        temporaryPoint.setCourse(0.0);
        return temporaryPoint;
    }
}
