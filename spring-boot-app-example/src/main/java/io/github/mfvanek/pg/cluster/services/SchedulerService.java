package io.github.mfvanek.pg.cluster.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerService {

    private final EmployeeService employeeService;
    private final AtomicLong counter = new AtomicLong();

    @Scheduled(fixedDelay = 100)
    public void scheduleStatisticsTask() {
        try {
            final int total = employeeService.getAll().size();
            log.info("Iteration {}. Total employees amount {}", counter.incrementAndGet(), total);
        } catch (Exception e) {
            log.warn("Iteration {}. Failed due to {}", counter.incrementAndGet(), e.getMessage());
        }
    }
}
