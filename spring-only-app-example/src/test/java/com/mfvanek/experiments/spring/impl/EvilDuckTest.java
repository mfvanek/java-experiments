package com.mfvanek.experiments.spring.impl;

import com.mfvanek.experiments.spring.interfaces.Duck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EvilDuckTest extends DuckBaseTest {

    @Test
    void shouldQuack() {
        final Duck zero = new EvilDuck(0, messageSourceMock);
        assertThat(zero.quack()).isEqualTo("Test Aha!!!");

        final Duck first = new EvilDuck(2, messageSourceMock);
        assertThat(first.quack()).isEqualTo("Test Aha-ha-ha!!!");

        final Duck second = new EvilDuck(3, messageSourceMock);
        assertThat(second.quack()).isEqualTo("Test Aha-ha-ha-ha!!!");
    }
}
