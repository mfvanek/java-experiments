package com.mfvanek.experiments.spring.impl;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.mockito.Mockito.when;

abstract class DuckBaseTest {

    protected final MessageSource messageSourceMock = Mockito.mock(MessageSource.class);

    @BeforeEach
    void setUpMessageSourceMock() {
        when(messageSourceMock.getMessage("mockery", null, Locale.getDefault()))
                .thenReturn("Test");
        when(messageSourceMock.getMessage("greeting", null, Locale.getDefault()))
                .thenReturn("Aloha");
    }
}
