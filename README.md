# java-experiments
Gradle multi-module project for experiments with Java

[![Java CI](https://github.com/mfvanek/java-experiments/actions/workflows/gradle.yml/badge.svg)](https://github.com/mfvanek/java-experiments/actions/workflows/gradle.yml)

## Modules
* [mongo-db-reactive-app-example](mongo-db-reactive-app-example)
* [Spring Boot 3 app with PostgreSQL cluster](spring-boot-app-example%2FREADME.md)

## Useful Java links

### Open Source

* [Open sourcing a library: how hard can that be? by Johan Hutting](https://www.youtube.com/watch?v=uR3rRvg9J5E)
* [The Secret Life of Maven Central by Jamie Coleman](https://www.youtube.com/watch?v=0A53s8ktN0M)

### How to tune GC and performance
* [Garbage Collection Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/toc.html)
* [Common misunderstanding of Metaspace and its sin on killing containers](https://masteranyfield.com/2021/05/12/common-misunderstanding-of-metaspace-and-its-sin-on-killing-containers/)
* [Mastering GC: tame the beast and make it your best ally](https://www.youtube.com/watch?v=oFhGtshu4ww)
* [Secrets of Performance Tuning Java](https://www.youtube.com/watch?v=wApqCjHWF8Q)
* [Java Performance Puzzlers](https://www.youtube.com/watch?v=wgQBz2Ldhvk)

#### RUS
* [How we learned to use Java in Docker](https://habr.com/ru/companies/hh/articles/450954/)
* [Configuring the memory of a JVM application in Kubernetes](https://habr.com/ru/companies/domclick/articles/691240/)

### Heap dumps

#### RUS

* [Владимир Ситников — Разбор сложных случаев OutOfMemoryError](https://www.youtube.com/watch?v=OE3wowy29mg)
* [Владимир Плизга — Путеводитель по анализу памяти JVM-приложений](https://www.youtube.com/watch?v=fPns2O-cnYQ)
* [Владимир Ситников — Анализ дампов памяти Java-приложений](https://www.youtube.com/watch?v=t_-WyfS9a7k)

### JVM internals
* [Memory footprint of a Java process by Andrei Pangin](https://www.youtube.com/watch?v=c755fFv1Rnk)
* [What is Metaspace?](https://stuefe.de/posts/metaspace/what-is-metaspace/)
* [What is Compressed Class Space?](https://stuefe.de/posts/metaspace/what-is-compressed-class-space/)
* [How does the default hashCode() work?](https://srvaroa.github.io/jvm/java/openjdk/biased-locking/2017/01/30/hashCode.html)

#### RUS
* [JVM TI how to make a "plugin" for a virtual machine by Andrei Pangin](https://www.youtube.com/watch?v=aiuKiE5-0g4)
* [Where do hashCode's legs grow from by Andrei Pangin](https://habr.com/ru/articles/165683/)

### Java Core
* [Method References in Java](https://www.baeldung.com/java-method-references)
* [Type Erasure in Java Explained](https://www.baeldung.com/java-type-erasure)

#### RUS
* [Владимир Ярославский — Удивительная история развития сортировки в JDK](https://www.youtube.com/watch?v=bzAHm79aHbA)

#### ClassLoaders
* [Understanding the Java Class Loader Starting from Java 9](https://sergiomartinrubio.com/articles/understanding-the-java-class-loader-starting-from-java-9/) [eng]
* [How does the Java ClassLoader system actually work?](https://habr.com/ru/articles/748758/) [rus]

### Java Modules (JPMS)
* [Java Modules in Real Life](https://www.youtube.com/watch?v=UqnwQp1uHuY)

### CompletableFuture

#### RUS

* [CompletableFuture. Deep dive](https://habr.com/ru/articles/784144/)
* [How to make CompletableFuture even faster by Sergey Kuksenko](https://www.youtube.com/watch?v=W7iK74YA5NM)

### Java Certification

* [11 Crazy Things I Didn't Know You Could Do With Java Until I Got My Java 11 Certification](https://www.youtube.com/watch?v=J3dqRwNQONE)

### JaCoCo

* [Java, Code Coverage & Their Best Friend: Bytecode by Evgeny Mandrikov](https://www.youtube.com/watch?v=nhxcUUthnk8)
* [Evgeny Mandrikov, Marc Hoffmann — Java Code Coverage mechanics](https://www.youtube.com/watch?v=Knb_qpp3dsQ)

### Logging

* [Logging Best Practices - MDC, Ingestion and Scale](https://talktotheduck.dev/logging-best-practices-mdc-ingestion-and-scale)

### Spring Boot

#### RUS

* [Boot yourself, Spring is coming by Evgeny Borisov](https://www.youtube.com/watch?v=yy43NOreJG4)
* [Spring Boot the Ripper by Evgeny Borisov](https://www.youtube.com/watch?v=zEdHFXr9D9Y)
* [The Curse of Spring Test by Evgeny Borisov](https://www.youtube.com/watch?v=7mZqJShu_3c)

### JUnit 5

* [JUnit 5 Conditional Test Execution with Annotations](https://www.baeldung.com/junit-5-conditional-test-execution)

#### RUS

* [Дмитрий Тучс — The art of JUnit extensions](https://www.youtube.com/watch?v=NFHdzKcU5OU)
* [Дмитрий Тучс — The art of JUnit extensions 2](https://www.youtube.com/watch?v=EhqGk_l99uI)

### Maven
* [Introduction to the Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
* [A Guide to Maven Artifact Classifiers](https://www.baeldung.com/maven-artifact-classifiers)
* [What I Wish I Knew About Maven Years Ago](https://www.youtube.com/watch?v=F3UA1Xt3jWE)

### Gradle

#### RUS
* [Кирилл Толкачёв — Релиз менеджмент с помощью Gradle](https://www.youtube.com/watch?v=l6ZfFFomEhk)
* [Иван Осипов — Kotlin DSL: теория и практика](https://www.youtube.com/watch?v=q_UM1EY2S5g)
* [Александр Белов — Gradle DSL изнутри](https://www.youtube.com/watch?v=vRzOr61xH60)

### People management

* [The Art of 1:1 Meetings — 6 Tips For Better Feedback](https://betterprogramming.pub/the-art-of-1-1-meetings-6-tips-for-better-feedback-8b80eb59b210)
* [Teamlead Roadmap RUS](https://tlroadmap.io/)

### Liquibase and Flyway

* [Flyway - Squashing Migrations](https://medium.com/att-israel/flyway-squashing-migrations-2993d75dae96)

### PostgreSQL

* [Migrating a production database without any downtime](https://teamplify.com/blog/zero-downtime-DB-migrations/)
* [Optimizing Postgres for write heavy workloads ft. Checkpoint and WAL configs](https://www.youtube.com/watch?v=t8rAOgDdH1U)
* [Tuning PostgreSQL for High Write Workloads](https://www.youtube.com/watch?v=xrMbzHdPLKM)
* [Performance oriented Spring Data JPA & Hibernate by Maciej Walkowiak](https://www.youtube.com/watch?v=exqfB1WaqIw)

#### RUS

* [PostgreSQL by Vladimir Borodin](https://www.youtube.com/watch?v=ejLzS6rVpkk)
* [The internal structure of PostgreSQL for practicing engineers](https://www.youtube.com/watch?v=jGOkSerUPw4)
* [Indexes in PostgreSQL. How to understand what to create](https://www.youtube.com/watch?v=ju9F8OvnL4E)
* [Expensive DELETE by Nikolay Samokhvalov](https://habr.com/ru/articles/523536/)
* [Changing the schema of PostgreSQL tables without long locks](https://habr.com/ru/companies/yandex/articles/435880/)
* [Дмитрий Фатов — Разгоняем вставку больших объемов данных Spring + PostgreSQL](https://www.youtube.com/watch?v=lpFDv50xn30)
* [Сергей Синдеев, Группа «Рексофт» — Hibernate, OOM и ооочень длинные запросы](https://www.youtube.com/watch?v=KUmzw-gAhCY)
* [Владимир Ситников — B-Tree индексы в базах данных на примере Spring Boot-приложений, PostgreSQL, JPA](https://www.youtube.com/watch?v=y-Wtyvme4gE)
* [Михаил Жилин — Как PostgreSQL может сделать больно, когда не ожидаешь?](https://www.youtube.com/watch?v=pTWfjyeQ5Ks)

#### Column migration from int to bigint
* [Column migration from INT to BIGINT in PostgreSQL](https://zemanta.github.io/2021/08/25/column-migration-from-int-to-bigint-in-postgresql/) [eng]
* [DBA: when serial is almost over](https://habr.com/ru/companies/tensor/articles/547740/) [rus]

#### The effect of the order of columns in tables on the size of PostgreSQL databases
* [eng](https://www.2ndquadrant.com/en/blog/on-rocks-and-sand/)
* [rus](https://habr.com/ru/articles/756074/)

### Kubernetes
* [You (probably) need liveness and readiness probes](https://developers.redhat.com/blog/2020/11/10/you-probably-need-liveness-and-readiness-probes)
* [Liveness probes are dangerous](https://srcco.de/posts/kubernetes-liveness-probes-are-dangerous.html)
* [Liveness and Readiness Probes](https://cloud.redhat.com/blog/liveness-and-readiness-probes)

### Load Testing

#### Gatling

* [Write Gatling Performance Tests with Java](https://rieckpil.de/write-gatling-performance-tests-with-java/)

##### RUS
* [Using Gatling. Introduction](https://habr.com/ru/companies/tinkoff/articles/655341/)

### Timeouts
* [eng](https://engineering.zalando.com/posts/2023/07/all-you-need-to-know-about-timeouts.html)
* [rus](https://habr.com/ru/articles/755750/)

### GraphQL

#### RUS
* [From REST to GraphQL: a 20-minute adventure](https://www.youtube.com/watch?v=90Ul1zDNMYA)

### Java/Spring Platform development
* [Building Spring Boot based modular libraries for 120 teams our findings](https://www.youtube.com/watch?v=7ji6DQ6COx0)
* [Delving Into Spring Boot Netflix: Netflix's Application Framework](https://www.youtube.com/watch?v=by-G0WX-LHA)
* [The Magic of Platforms](https://platformengineering.org/talks-library/the-magic-of-platforms)

### IntelliJ IDEA
* [IntelliJ IDEA Tips & Tricks by Anton Arhipov](https://www.youtube.com/watch?v=FD8FtyX-nbQ)
* [Debugging applications with IntelliJ IDEA by Anton Arhipov](https://www.youtube.com/watch?v=4rb5CxjPhgU)

#### RUS
* [Антон Архипов — Эффективная работа с IDE(A)](https://www.youtube.com/watch?v=_rj7dx6c5R8)
* [Антон Архипов - Анализ и отладка приложений в IntelliJ IDEA](https://www.youtube.com/watch?v=p6nXKii-GEo)

## Useful Kotlin links
* [Public API challenges in Kotlin](https://jakewharton.com/public-api-challenges-in-kotlin/)
