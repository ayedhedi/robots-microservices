package lu.rbc.robotsstore.query.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;
import lu.rbc.robotsstore.query.domain.model.Robot;
import lu.rbc.robotsstore.query.repository.RobotRepository;
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
@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;

    @Autowired
    public RobotServiceImpl(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }


    /**
     * Finds and returns all the robots from the repository
     * @return a {@link java.util.List list} of {@link Robot robots}
     */
    @Override
    public List<Robot> getRobots() {
        log.info("[SRV][ROBOT] Finding the list of robots");
        return StreamSupport.stream(robotRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Find one robot by its id
     * @param id the id of the {@link Robot robot} to find
     * @return an {@link Optional optional}
     */
    @Override
    public Optional<Robot> findRobotById(long id) {
        log.info("[SRV][ROBOT] Looking for robot {}", id);
        Robot robot = robotRepository.findOne(id);
        return robot == null ? Optional.empty() : Optional.of(robot);
    }

    /**
     * Find one page of robots
     * @param pageable the size and number of the page to return
     * @return one page of robot
     */
    @Override
    public Page<Robot> getByPage(Pageable pageable) {
        log.info("[SRV][ROBOT] Looking for page {}/{}",
                pageable.getPageNumber(), pageable.getPageSize());
        return robotRepository.findAll(pageable);
    }

}
