package lu.rbc.robotsstore.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import lu.rbc.robotsstore.query.domain.dto.BasicRobot;
import lu.rbc.robotsstore.query.domain.model.Robot;
import lu.rbc.robotsstore.query.exception.RobotNotFoundException;
import lu.rbc.robotsstore.query.service.RobotService;

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
@RequestMapping("/store/query/robots")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_USER')")
public class RobotController {

    private final RobotService robotService;
    private final ConversionService conversionService;

    @Autowired
    public RobotController(@Qualifier("ServiceConverter") ConversionService conversionService,
                           RobotService robotService) {

        this.robotService = robotService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public List<BasicRobot> findRobots() {
        log.info("[API][GET] find robots");
        return robotService.getRobots()
                .stream()
                .map(robot -> conversionService.convert(robot, BasicRobot.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/pageable")
    public Page<BasicRobot> findByPage(Pageable pageable){
        return robotService.getByPage(pageable)
                .map(robot -> conversionService.convert(robot, BasicRobot.class));

    }

    @GetMapping("/{id}")
    public BasicRobot getRobot(@PathVariable long id) throws RobotNotFoundException {
        log.info("[API][GET] get robot {} ", id);
        Optional<Robot> robotOptional = robotService.findRobotById(id);

        if (robotOptional.isPresent()) {
            return conversionService.convert(robotOptional.get(), BasicRobot.class);
        }else {
            throw new RobotNotFoundException(id);
        }
    }

}
