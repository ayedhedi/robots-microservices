package lu.rbc.robotsstore.query.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

import lu.rbc.robotsstore.query.converter.RobotConverter;

/**
 * Created by Hedi Ayed on 27/02/2018.
 *
 *
 * --------------------
 *
 * @author Hedi Ayed
 */
@Configuration
public class ApplicationConfigs {

    @Bean(name = "ServiceConverter")
    public ConversionServiceFactoryBean createServiceConverter() {
        ConversionServiceFactoryBean converter = new ConversionServiceFactoryBean();

        RobotConverter robotConverter = new RobotConverter();

        Set<Converter> converters = new HashSet<>();
        converters.add(robotConverter);

        converter.setConverters(converters);
        return converter;
    }
}
