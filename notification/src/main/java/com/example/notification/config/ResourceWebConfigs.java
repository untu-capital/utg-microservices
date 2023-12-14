package com.example.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfigs implements WebMvcConfigurer {
  final Environment environment;

  public ResourceWebConfigs(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    String location = environment.getProperty("app.file.storage.mapping");

    registry.addResourceHandler("/signatures/**").addResourceLocations(location);
  }
}
