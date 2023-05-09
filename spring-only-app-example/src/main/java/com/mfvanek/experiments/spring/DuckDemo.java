package com.mfvanek.experiments.spring;

import com.mfvanek.experiments.spring.interfaces.Duck;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
class DuckDemo {

    @Autowired
    private List<Duck> ducks;

    private int count;

    void test() {
        for (Duck duck : ducks) {
            System.out.println(String.format("%s says '%s'", AopUtils.getTargetClass(duck).getSimpleName(), duck.quack()));
        }
    }
}
