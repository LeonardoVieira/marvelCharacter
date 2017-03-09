package com.marvel.character;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

import com.marvel.character.web.validator.UserValidator;

@EnableCaching
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public static Validator configurationPropertiesValidator() {
		return new UserValidator();
	}

	/**
	 * Spring Cache configuring method
	 * 
	 * @return {@link CacheManager}
	 */
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(createCache());
		return cacheManager;
	}

	/**
	 * Creates the list containing the names of the caches that will be used in
	 * the application
	 * 
	 * @return {@link List}<{@link ConcurrentMapCache}>
	 */
	private List<ConcurrentMapCache> createCache() {
		List<ConcurrentMapCache> caches = new ArrayList<ConcurrentMapCache>();

		caches.add(new ConcurrentMapCache("characters"));
		caches.add(new ConcurrentMapCache("profile"));

		return caches;
	}
}
