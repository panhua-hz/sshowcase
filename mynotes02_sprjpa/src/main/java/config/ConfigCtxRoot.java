package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(ConfigCtxDatabase.class)
@EnableJpaRepositories(basePackages={"repository"})
@ComponentScan(basePackages={"services"})
public class ConfigCtxRoot {

}
