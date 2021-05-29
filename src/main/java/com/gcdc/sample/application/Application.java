package com.gcdc.sample.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.gcdc.sample.*", "com.gcdc.openapi.api.*"})
@Configuration
@EnableLdapRepositories(basePackages = "com.gcdc.sample.**")
@PropertySource("classpath:application.properties")
public class Application {
	
	@Autowired
	private Environment env;

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(env.getRequiredProperty("ldap.url"));
        contextSource.setBase(env.getRequiredProperty("ldap.partitionSuffix"));
        contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
        contextSource.setPassword(env.getRequiredProperty("ldap.password"));
        return contextSource;
	}
	
	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
