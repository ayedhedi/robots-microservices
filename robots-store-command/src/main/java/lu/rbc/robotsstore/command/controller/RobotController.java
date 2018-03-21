package lu.rbc.robotsstore.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lu.rbc.robotsstore.command.domain.dto.BasicRobot;
import lu.rbc.robotsstore.command.domain.model.Robot;
import lu.rbc.robotsstore.command.exception.InvalidDataException;
import lu.rbc.robotsstore.command.exception.RobotNotFoundException;
import lu.rbc.robotsstore.command.service.impl.RobotServiceImpl;

/**
 * Created by Hedi Ayed on 26/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Slf4j
@RestController
@RequestMapping("/store/command/robots")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RobotController {

    private final RobotServiceImpl robotService;
    private final ConversionService conversionService;

    @Autowired
    public RobotController(@Qualifier("ServiceConverter") ConversionService conversionService,
                           RobotServiceImpl robotService) {

        this.robotService = robotService;
        this.conversionService = conversionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicRobot createRobot(@RequestBody BasicRobot robot) throws InvalidDataException {
        log.info("[API][POST] create new robot");
        Robot newRobot = robotService.createRobot(conversionService.convert(robot, Robot.class));
        return conversionService.convert(newRobot, BasicRobot.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BasicRobot updateRobot(@PathVariable long id,
                                  @RequestBody BasicRobot robot)
            throws RobotNotFoundException, InvalidDataException {

        log.info("[API][GET] update robot {} ", id);
        Robot newRobot = robotService.updateRobot(id, conversionService.convert(robot, Robot.class));
        return conversionService.convert(newRobot, BasicRobot.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRobot(@PathVariable long id) throws RobotNotFoundException {
        log.info("[API][GET] delete robot {} ", id);
        robotService.deleteRobot(id);
    }
}
