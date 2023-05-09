package com.mfvanek.experiments.spring.impl;

import com.mfvanek.experiments.spring.interfaces.Duck;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

public class EvilDuck implements Duck {

    private final int count;
    private final MessageSource messageSource;

    public EvilDuck(int count, MessageSource messageSource) {
        this.count = count;
        this.messageSource = messageSource;
    }

    @Override
    public String quack() {
        StringBuilder builder = new StringBuilder(messageSource.getMessage("mockery", null, Locale.getDefault()));
        builder.append(" Aha");
        for (int i = 0; i < count; ++i) {
            builder.append("-ha");
        }
        builder.append("!!!");
        return builder.toString();
    }

    @PostConstruct
    void init() {
        System.out.println(this.getClass().getSimpleName() + " init() is called");
    }

    @PreDestroy
    void destroy() {
        System.out.println(this.getClass().getSimpleName() + " destroy() is called");
    }
}
