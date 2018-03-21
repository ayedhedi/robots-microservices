package lu.rbc.robotsstore.command.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

import lu.rbc.robotsstore.command.domain.model.Robot;


/**
 * Created by Hedi Ayed on 26/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface RobotRepository extends MongoRepository<Robot, Long> {

    Optional<Robot> findByCode(String code);
}
