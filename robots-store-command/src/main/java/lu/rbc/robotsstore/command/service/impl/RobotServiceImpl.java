package lu.rbc.robotsstore.command.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import lu.rbc.robotsstore.command.domain.model.Robot;
import lu.rbc.robotsstore.command.exception.InvalidDataException;
import lu.rbc.robotsstore.command.exception.RobotNotFoundException;
import lu.rbc.robotsstore.command.repository.RobotRepository;
import lu.rbc.robotsstore.command.service.RobotService;

/**
 * Created by Hedi Ayed on 26/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Slf4j
@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;

    @Autowired
    public RobotServiceImpl(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }


    /**
     * Create and save a new Robot
     * @param robot the robot to save
     * @return the persisted {@link Robot robot} object
     */
    @Override
    public Robot createRobot(Robot robot) throws InvalidDataException {
        log.info("[SRV][ROBOT] Creating new robot instance {}", robot);
        robot.setId((long)(Math.random()*1000000000));

        //cannot create robot with the same code
        Optional<Robot> robotOptional = robotRepository.findByCode(robot.getCode());
        if (robotOptional.isPresent()) {
            log.warn("Cannot create robot: the same code is user {}", robot.getCode());
            throw new InvalidDataException("Robot code is used");
        }else {
            return robotRepository.save(robot);
        }
    }


    /**
     * Update a robot, given by its id/
     * @param id the id of the robot to update
     * @param newRobot the new robot
     * @return the new persisted robot
     * @throws RobotNotFoundException: thrown if the robot not found
     */
    @Override
    public Robot updateRobot(long id, Robot newRobot) throws RobotNotFoundException, InvalidDataException {
        log.info("[SRV][ROBOT] Updating robot {} ", id);
        Robot old =  robotRepository.findOne(id);
        if (old == null) {
            throw new RobotNotFoundException(id);
        }else {

            //if code is modified and already exists throws error
            if (!Objects.equals(old.getCode(), newRobot.getCode()) &&
                    robotRepository.findByCode(newRobot.getCode()).isPresent()){
                throw new InvalidDataException("Robot code is used");
            }


            old.setAvailable(newRobot.isAvailable());
            old.setImage(newRobot.getImage());
            old.setQuantity(newRobot.getQuantity());
            old.setPrice(newRobot.getPrice());
            old.setDescription(newRobot.getDescription());
            old.setBrand(newRobot.getBrand());
            old.setCode(newRobot.getCode());
            old.setCategory(newRobot.getCategory());
            old.setName(newRobot.getName());
            old.setFunctions(new HashSet<>(newRobot.getFunctions()));
            return robotRepository.save(old);
        }
    }

    /**
     * Delete robot with an id
     * @param id the id of the robot to delete
     * @throws RobotNotFoundException
     */
    @Override
    public void deleteRobot(long id) throws RobotNotFoundException {
        log.info("[SRV][ROBOT] Delete robot {} ", id);
        Robot robot = robotRepository.findOne(id);
        if (robot == null) {
            throw new RobotNotFoundException(id);
        }else {
            robotRepository.delete(id);
        }
    }

}
