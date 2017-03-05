/**
 * 
 */
package com.marvel.character;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Project persistence configuration class
 * 
 * @author Leonardo Vieira
 * @since 01-2017
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.marvel.character.repository")
public class PersistenceConfig {

	/**
	 * @return {@link DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:avenuecode;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		return dataSource;
	}

	/**
	 * @return {@link JpaVendorAdapter}
	 */
	@Bean
	public JpaVendorAdapter jpaVendorApapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		return vendorAdapter;
	}

	/**
	 * @return {@link LocalContainerEntityManagerFactoryBean}
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.marvel.character.model");
		emf.setJpaVendorAdapter(jpaVendorApapter());
		emf.setJpaProperties(jpaProperties());

		return emf;
	}

	/**
	 * @param entityManager
	 * @return {@link PlatformTransactionManager}
	 */
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManager);

		return transactionManager;
	}

	/**
	 * @param dataSource {@link DataSource}
	 * @return {@link DataSourceInitializer}
	 */
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("data.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(true);
		return dataSourceInitializer;
	}

	/**
	 * JPA properties configuration method
	 * 
	 * @return {@link Properties}
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		
		return properties;
	}
}