package io.github.mfvanek.reactive.mongodb.example.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {MongoInitializer.class})
@ActiveProfiles("test")
public abstract class BaseTest {

    @LocalServerPort
    protected int port;

    @LocalManagementPort
    protected int actuatorPort;

    @Autowired
    protected WebTestClient webTestClient;
}
