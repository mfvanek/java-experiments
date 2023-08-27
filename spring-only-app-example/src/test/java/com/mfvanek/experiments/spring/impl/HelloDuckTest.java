package com.mfvanek.experiments.spring.impl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloDuckTest extends DuckBaseTest {

    @Test
    void shouldQuack() {
        final HelloDuck zero = new HelloDuck();
        zero.setMessageSource(messageSourceMock);
        assertThat(zero.quack()).isEqualTo("Aloha");

        final HelloDuck first = new HelloDuck();
        first.init();
        first.setMessageSource(messageSourceMock);
        assertThat(first.quack()).isEqualTo("Aloha!!!!!");
    }
}
