package io.github.mfvanek.pg.cluster.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.Comment;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(ClockAwareEntityListener.class)
public abstract class BaseEntity {

    @Id
    @NotNull
    @Comment("primary key")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Comment("date and time when row was created")
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Comment("date and time when row was last modified")
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
}
