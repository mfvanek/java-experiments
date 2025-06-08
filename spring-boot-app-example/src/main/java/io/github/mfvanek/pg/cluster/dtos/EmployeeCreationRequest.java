package io.github.mfvanek.pg.cluster.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString
@SuperBuilder
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

    @JsonCreator
    public EmployeeCreationRequest(String firstName,
                                   String lastName,
                                   int standardHoursPerDay,
                                   BigDecimal salaryPerHour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.standardHoursPerDay = standardHoursPerDay;
        this.salaryPerHour = salaryPerHour;
    }
}
