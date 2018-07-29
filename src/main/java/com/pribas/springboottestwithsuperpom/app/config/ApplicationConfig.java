package com.pribas.springboottestwithsuperpom.app.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Keeps the application configuration read from resources/application.properties.
 * <p>Uses lombok to generate getters/setters and ToString</p>
 */
@Component
@Getter
@Setter
@ToString
public class ApplicationConfig {

  @Value("${config-service-url}")
  private String configServiceUrl;

  @Value("${example-service-url}")
  private String exampleServiceUrl;

  @Value("${config-service-identifier}")
  private String configServiceIdentifier;

  @Value("${user-management-url}")
  private String userManagementUrl;

  @Value("${application.name}")
  private String applicationName;

  @Value("${build.version}")
  private String buildVersion;
}
