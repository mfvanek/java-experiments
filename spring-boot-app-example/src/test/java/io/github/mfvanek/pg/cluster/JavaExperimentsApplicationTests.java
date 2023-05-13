package io.github.mfvanek.pg.cluster;

import io.github.mfvanek.pg.cluster.support.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class JavaExperimentsApplicationTests extends BaseTest {

    @Autowired
    private ApplicationContext context;

    @Test
	void contextLoads() {
        assertThat(context.getEnvironment().containsProperty("spring.datasource.url"))
                .isTrue();
	}
}
