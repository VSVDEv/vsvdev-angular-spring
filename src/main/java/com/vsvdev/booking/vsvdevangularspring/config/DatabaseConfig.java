package com.vsvdev.booking.vsvdevangularspring.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.vsvdev.booking.vsvdevangularspring.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
