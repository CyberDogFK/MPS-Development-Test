package application.springboot;

import application.service.AirplaneCharacteristicsService;
import application.service.FlightService;
import application.service.logical.PlaneCalculation;
import application.service.TemporaryPointService;
import application.service.WayPointService;
import application.service.impl.AirplaneServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"application"})
@EnableJpaRepositories("application.dao")
@EntityScan("application.model")
public class Injecting implements CommandLineRunner {
    private final PlaneCalculation planeCalculation;
    private final AirplaneServiceImpl airplaneService;
    private final AirplaneCharacteristicsService airplaneCharacteristicsService;
    private final FlightService flightService;
    private final TemporaryPointService temporaryPointService;
    private final WayPointService wayPointService;
    private final ScheduledTask scheduledTask;


    public Injecting(PlaneCalculation planeCalculation, AirplaneServiceImpl airplaneService,
                     AirplaneCharacteristicsService airplaneCharacteristicsService,
                     FlightService flightService, TemporaryPointService temporaryPointService,
                     WayPointService wayPointService, ScheduledTask scheduledTask) {
        this.planeCalculation = planeCalculation;
        this.airplaneService = airplaneService;
        this.airplaneCharacteristicsService = airplaneCharacteristicsService;
        this.flightService = flightService;
        this.temporaryPointService = temporaryPointService;
        this.wayPointService = wayPointService;
        this.scheduledTask = scheduledTask;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(flightService.getAll());
        scheduledTask.doTask1();
        System.out.println(flightService.getAll());
        scheduledTask.doTask2();
        System.out.println(flightService.getAll());
        scheduledTask.doTask3();
        System.out.println(flightService.getAll());
    }
}
