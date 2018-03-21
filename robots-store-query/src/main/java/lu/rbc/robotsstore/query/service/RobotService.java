package lu.rbc.robotsstore.query.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import lu.rbc.robotsstore.query.domain.model.Robot;
import lu.rbc.robotsstore.query.exception.InvalidDataException;
import lu.rbc.robotsstore.query.exception.RobotNotFoundException;

/**
 * Created by Hedi Ayed on 05/03/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface RobotService {


    List<Robot> getRobots();

    Optional<Robot> findRobotById(long id);

    Page<Robot> getByPage(Pageable pageable);
}
