package lu.rbc.robotsstore.query.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import lu.rbc.robotsstore.query.domain.model.Robot;


/**
 * Created by Hedi Ayed on 26/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
public interface RobotRepository extends PagingAndSortingRepository<Robot, Long> {

}
