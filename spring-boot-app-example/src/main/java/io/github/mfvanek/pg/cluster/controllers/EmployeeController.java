package io.github.mfvanek.pg.cluster.controllers;

import io.github.mfvanek.pg.cluster.dtos.EmployeeCreationRequest;
import io.github.mfvanek.pg.cluster.dtos.EmployeeDto;
import io.github.mfvanek.pg.cluster.services.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final UUID id) {
        final Optional<EmployeeDto> employee = employeeService.findById(id);
        final HttpStatus status = employee.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(employee.orElse(EmployeeDto.builder().build()), status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody final EmployeeCreationRequest newEmployee,
                                                      final UriComponentsBuilder uriComponentsBuilder) {
        final EmployeeDto employee = employeeService.create(newEmployee);
        final UriComponents uriComponents = uriComponentsBuilder.path("/v1/employee/{id}")
                .buildAndExpand(employee.getId());
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(employee, headers, HttpStatus.CREATED);
    }
}
