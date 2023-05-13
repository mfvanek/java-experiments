package io.github.mfvanek.pg.cluster.services;

import io.github.mfvanek.pg.cluster.dtos.EmployeeCreationRequest;
import io.github.mfvanek.pg.cluster.dtos.EmployeeDto;
import io.github.mfvanek.pg.cluster.entities.Employee;
import io.github.mfvanek.pg.cluster.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Nonnull
    public Optional<EmployeeDto> findById(@Nonnull final UUID id) {
        Objects.requireNonNull(id, "id cannot be null");
        return employeeRepository.findById(id)
                .map(EmployeeService::mapToDto);
    }

    @Nonnull
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeService::mapToDto)
                .toList();
    }

    @Nonnull
    @Transactional
    public EmployeeDto create(@Nonnull final EmployeeCreationRequest request) {
        final Employee employee = Employee.builder()
                .id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .standardHoursPerDay(request.getStandardHoursPerDay())
                .salaryPerHour(request.getSalaryPerHour())
                .build();
        return mapToDto(employeeRepository.save(employee));
    }

    private static EmployeeDto mapToDto(@Nonnull final Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .standardHoursPerDay(employee.getStandardHoursPerDay())
                .salaryPerHour(employee.getSalaryPerHour())
                .build();
    }
}
