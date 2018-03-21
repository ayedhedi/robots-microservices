package lu.rbc.robotsstore.query.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lu.rbc.robotsstore.query.domain.enums.RobotCategory;
import lu.rbc.robotsstore.query.domain.enums.RobotFunction;

/**
 * Created by Hedi Ayed on 26/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "robots")
public class Robot {
    @Id
    private Long id;
    private String name;
    private RobotCategory category;
    private String code;
    private String brand;
    private String description;
    private float price;
    private int quantity;
    private String image;
    private boolean available;
    private Collection<RobotFunction> functions;
}
