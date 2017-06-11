package config;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
public class ConfigCtxDatabase {
	
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("init.sql").build();
    }

//    @Bean
//    public JdbcOperations jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
    
    //JPA Configure
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
      emf.setDataSource(dataSource);
      emf.setPersistenceUnitName("mynote_jpa");
      emf.setJpaVendorAdapter(jpaVendorAdapter);
      emf.setPackagesToScan("domain");
      return emf;
    }
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
      adapter.setDatabase(Database.H2);
      adapter.setShowSql(true);
      adapter.setGenerateDdl(false);
      adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
      return adapter;
    }
    
    //Inner class with @Configuration will automatically import?
    @Configuration
    @EnableTransactionManagement
    public static class TransactionConfig {
      @Inject
      private EntityManagerFactory entityManagerFactory;

      @Bean
      public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
      }    
    }
}
