package io.github.mfvanek.reactive.mongodb.example.support;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final MongoDBContainer CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:8.2.5"));

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        CONTAINER.start();

        TestPropertyValues.of(
            "spring.data.mongodb.uri=" + CONTAINER.getReplicaSetUrl()
        ).applyTo(context.getEnvironment());
    }
}
