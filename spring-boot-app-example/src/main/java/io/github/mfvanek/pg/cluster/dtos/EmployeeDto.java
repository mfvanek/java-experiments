package io.github.mfvanek.pg.cluster.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@SuperBuilder
@Getter
@ToString
public final class EmployeeDto {

    private final UUID id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String firstName;
    private final String lastName;
    private final int standardHoursPerDay;
    private final BigDecimal salaryPerHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDto that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
