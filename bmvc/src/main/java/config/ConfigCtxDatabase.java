package config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCtxDatabase {
	
	/*
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("init.sql").build();
    }

    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    */
}
