package com.user.config;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {

		PGPoolingDataSource source = new PGPoolingDataSource();
		source.setDatabaseName("logindb");
		source.setUser("loginuser2");
		source.setPassword("loginuser");
		source.setMaxConnections(10);
		source.setServerName("rhishipriya.home");
		source.setPortNumber(5432);

		return source;
	}
}
