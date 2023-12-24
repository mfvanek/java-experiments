package io.github.mfvanek.pg.cluster.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString
@SuperBuilder
@RequiredArgsConstructor
public final class EmployeeCreationRequest {

    @NotNull
    @NotBlank
    private final String firstName;

    @NotNull
    @NotBlank
    private final String lastName;

    @Min(1)
    @Max(8)
    private final int standardHoursPerDay;

    @NotNull
    @DecimalMax("5000.00")
    @DecimalMin("100.00")
    private final BigDecimal salaryPerHour;
}
