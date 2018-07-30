package com.pribas.springboottestwithsuperpom.app;

import com.pribas.springboottestwithsuperpom.app.beans.Post;
import com.pribas.springboottestwithsuperpom.app.config.ApplicationConfig;
import com.pribas.springboottestwithsuperpom.app.handler.ExampleConsumer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main application class.
 *
 * <p>
 * Setups swagger documentation.
 * </p>
 */
@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
@EnableSwagger2
public class SpringboottestApplication implements ApplicationRunner {

  private final ApplicationConfig applicationConfig;
  private final ApplicationContext applicationContext;

  @Autowired
  public SpringboottestApplication(ApplicationConfig applicationConfig, ApplicationContext applicationContext) {
    this.applicationConfig = applicationConfig;
    this.applicationContext = applicationContext;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringboottestApplication.class, args);
  }

  /**
   * Populates swagger documentation by checking given base package.
   * <p>
   * Has to be package private at least, because it should be overridable (spring boot)
   * </p>
   *
   * @return Returns Docket which is needed for swagger docs.
   */
  @Bean
  Docket swaggerSpringMvcPlugin() {
    return new Docket(DocumentationType.SWAGGER_2)//
        .apiInfo(apiInfo())
        .directModelSubstitute(LocalDateTime.class, String.class)//
        .directModelSubstitute(LocalDate.class, String.class)//
        .select().apis(RequestHandlerSelectors.basePackage("com.pribas"))//
        .build();
  }

  /**
   * Creates a rest template to consume REST services.
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.setConnectTimeout(3000).setReadTimeout(3000)
        .uriTemplateHandler(new DefaultUriBuilderFactory(applicationConfig.getExampleServiceUrl())).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(applicationConfig.getApplicationName())
        .version(applicationConfig.getBuildVersion())
        .build();
  }

  @Override
  public void run(ApplicationArguments args) {
    applicationContext.getBean(ExampleConsumer.class).getPost(null);
    applicationContext.getBean(ExampleConsumer.class).getPost(Post.builder().id(1).title("foo").body("bar")
        .userId(1).build());
  }
}
