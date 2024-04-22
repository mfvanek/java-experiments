package io.github.mfvanek.bom.example.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Nonnull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseTest {

    @LocalServerPort
    protected int port;

    @LocalManagementPort
    protected int actuatorPort;

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    private SecurityProperties securityProperties;

    protected final void setUpBasicAuth(@Nonnull final HttpHeaders httpHeaders) {
        httpHeaders.setBasicAuth(securityProperties.getUser().getName(), securityProperties.getUser().getPassword());
    }
}
