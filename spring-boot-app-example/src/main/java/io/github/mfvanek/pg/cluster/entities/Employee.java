package io.github.mfvanek.pg.cluster.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "employees")
@Comment("Table for storing employees data")
public class Employee extends BaseEntity {

    @NotNull
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "hours_per_day", nullable = false)
    private int standardHoursPerDay;

    @NotNull
    @DecimalMax("5000.00")
    @DecimalMin("100.00")
    @Column(name = "salary_per_hour", nullable = false)
    private BigDecimal salaryPerHour;
}
