package io.github.mfvanek.pg.cluster.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Clock;
import java.time.LocalDateTime;
import javax.annotation.Nonnull;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ClockAwareEntityListener {

    // Couldn't use constructor injection here
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private Clock clock;

    @PrePersist
    public void initCreatedAt(@Nonnull final BaseEntity entity) {
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now(clock));
        }
    }

    @PreUpdate
    public void initUpdatedAt(@Nonnull final BaseEntity entity) {
        if (entity.getId() != null && entity.getCreatedAt() != null) {
            entity.setUpdatedAt(LocalDateTime.now(clock));
        }
    }
}
