package com.mfvanek.experiments.spring;

import com.mfvanek.experiments.spring.entities.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class)) {
            final DuckDemo duckDemo = context.getBean(DuckDemo.class);
            duckDemo.test();

            Locale.setDefault(Locale.FRENCH);
            duckDemo.test();

            final JdbcTemplate template = context.getBean(JdbcTemplate.class);
            int res = template.update("UPDATE users SET name = 'Ivan' WHERE id = 1");
            System.out.println("res = " + res);

            final List<User> users = template.query("select * from users",
                    (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
            users.forEach(System.out::println);
        }
    }
}
