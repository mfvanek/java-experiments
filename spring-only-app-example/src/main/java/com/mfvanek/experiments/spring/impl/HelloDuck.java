package com.mfvanek.experiments.spring.impl;

import com.mfvanek.experiments.spring.interfaces.Duck;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

@Component
@Setter(AccessLevel.PACKAGE)
class HelloDuck implements Duck {

    @Autowired
    private MessageSource messageSource;

    private int count;

    @Override
    public String quack() {
        StringBuilder builder = new StringBuilder(messageSource.getMessage("greeting", null, Locale.getDefault()));
        for (int i = 0; i < count; ++i) {
            builder.append('!');
        }
        return builder.toString();
    }

    @PostConstruct
    void init() {
        System.out.println(this.getClass().getSimpleName() + " init() is called");
        count = 5;
    }

    @PreDestroy
    void destroy() {
        System.out.println(this.getClass().getSimpleName() + " destroy() is called");
    }
}
