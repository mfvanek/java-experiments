# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Gradle (Kotlin DSL) multi-module sandbox for Java experiments. Java 25 toolchain, Gradle 9.x wrapper, Spring Boot 4 / Spring Framework 7. Group `io.github.mfvanek`.

## Commands

On Windows use `.\gradlew.bat` (or `./gradlew` from Git Bash).

```shell
./gradlew build                                   # what CI runs: compile, tests, JaCoCo, PIT mutation testing + aggregate reports
./gradlew check                                   # tests + coverage verification + aggregated coverage report, without the build-level pitest aggregation
./gradlew :db-commons:test                        # tests of one module
./gradlew :spring-boot-app-example:test --tests "io.github.mfvanek.pg.cluster.controllers.EmployeeControllerTest"
./gradlew :computer-science:test --tests "*SomeTest.someMethod"
./gradlew :computer-science:pitest                # mutation testing for one module
./gradlew :spring-boot-app-example:bootRun -PjvmArgs="-Xmx500m ..."   # -PjvmArgs is split on whitespace into JVM args
./gradlew dependencyUpdates                       # ben-manes plugin; rejects non-stable versions
```

There is no separate lint task: **Error Prone runs as part of every `compileJava`** and fails the build on errors (`--should-stop=ifError=FLOW`). `build` also triggers `pitest` in modules that apply the pitest convention, so it is noticeably slower than `test`.

**Docker is required** for most tests: PostgreSQL and MongoDB are provided via Testcontainers. Note that `spring-boot-app-example` starts its PostgreSQL cluster via Testcontainers in *main* code (`DatabaseConfig` builds a `PostgreSqlClusterWrapper` from `pg-index-health-testing`), so `bootRun` needs Docker too.

Tests are retried once on failure (gradle test-retry plugin, `failOnPassedAfterRetry=false`), so a flaky test can pass silently on a second attempt.

## Build structure

- `buildSrc/src/main/kotlin/` holds convention plugins applied by modules:
  - `java-experiments.java-conventions` — Java 25 toolchain, Lombok, Error Prone (+ slf4j checks), JUnit Platform, AssertJ, JSR-305, JaCoCo, test-retry, `-parameters`.
  - `java-experiments.pitest` — PIT mutation testing (JUnit 5 plugin); hooks `pitest` into `build`.
  - `java-experiments.bom-publish` — Maven publication for `java-platform` BOM modules.
- Dependency versions are centralized in BOM modules, not in individual module build files — modules declare dependencies without versions:
  - `internal-bom` — third-party BOMs (JUnit, Testcontainers, pg-index-health, Mockito, AssertJ) plus version constraints for individual libraries. Used by non-Spring-Boot modules.
  - `internal-spring-boot-4-bom` — `internal-bom` + Spring Boot dependencies. Used by Spring Boot modules.
  - The Spring Boot version lives in `gradle/libs.versions.toml` (`spring-boot-v4`), referenced as `libs.plugins.spring.boot.v4` / `libs.spring.boot.v4.dependencies`.
  - Exceptions: Error Prone, JaCoCo and PIT tool versions are in `buildSrc` convention plugins; `spring-only-app-example` imports `spring-framework-bom` directly.
- Root `build.gradle.kts` aggregates JaCoCo (`testCodeCoverageReport`, all modules except `spring-boot-*` and `*-bom`) and PIT (`pitestReportAggregate`).

## Modules

- `db-commons` — shared `HikariDataSourceProvider` used by the PostgreSQL apps.
- `console-app-example` — plain Java console app against a PostgreSQL cluster (Testcontainers).
- `spring-boot-app-example` — Spring Boot web/JPA app on a PostgreSQL primary/standby cluster; exposes `/api/v1/employee` and `/api/v1/cluster/failover` (switches primary). Actuator runs on a separate management port. Tests extend `support/BaseTest` (random ports, `test` profile, `WebTestClient`, a `MutableClock` replacing the `Clock` bean). Includes pg-index-health DB structure static analysis tests.
- `mongo-db-reactive-app-example` — Spring Boot WebFlux + reactive MongoDB.
- `spring-only-app-example` — Spring Framework without Boot, embedded H2.
- `computer-science` — algorithm/data-structure experiments.

## Conventions

- `.editorconfig`: 4-space indent, CRLF line endings, max line length 199; Java imports ordered as `*`, then `java.**`/`javax.**`, then static imports.
- Nullability annotations: `jakarta.annotation.Nonnull` and friends on parameters/return types; `final` on parameters and locals is the prevailing style.
