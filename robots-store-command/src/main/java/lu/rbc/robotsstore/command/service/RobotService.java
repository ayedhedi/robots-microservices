package lu.rbc.robotsstore.command.service;

import lu.rbc.robotsstore.command.domain.model.Robot;
import lu.rbc.robotsstore.command.exception.InvalidDataException;
import lu.rbc.robotsstore.command.exception.RobotNotFoundException;


/**
 * Created by Hedi Ayed on 05/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface RobotService {

    Robot createRobot(Robot robot) throws InvalidDataException;

    Robot updateRobot(long id, Robot newRobot) throws RobotNotFoundException, InvalidDataException;

    void deleteRobot(long id) throws RobotNotFoundException;

}
