package com.mfvanek.experiments.spring.impl;

import com.mfvanek.experiments.spring.interfaces.Duck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

@Component
@Scope("prototype")
class WildDuck implements Duck {

    private int count;
    private final MessageSource messageSource;

    @Autowired
    WildDuck(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String quack() {
        StringBuilder builder = new StringBuilder(messageSource.getMessage("who_i_am", null, Locale.getDefault()));
        builder.append(" Qua-qua-quack");
        for (int i = 0; i < count; ++i) {
            builder.append('!');
        }
        return builder.toString();
    }

    @PostConstruct
    void init() {
        System.out.println(this.getClass().getSimpleName() + " init() is called");
        count = 1;
    }

    @PreDestroy
    void destroy() {
        System.out.println(this.getClass().getSimpleName() + " destroy() is called");
    }
}
