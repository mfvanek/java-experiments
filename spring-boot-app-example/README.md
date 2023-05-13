# spring-boot-app-example
Demo app with PostgreSQL cluster (Spring Boot)

## Requirements
Java 17+  
Spring Boot 3.0+

## API
### Actuator
```shell
curl http://localhost:8090/actuator/health
```

### Endpoints
```shell
curl http://localhost:8080/api/v1/employee/dcffa631-595a-44e6-8f8c-1c077de895bf
```

```shell
curl -i -X POST -d "{\"firstName\": \"John\",\"lastName\": \"Wick\",\"standardHoursPerDay\": 8,\"salaryPerHour\": 5000}" http://localhost:8080/api/v1/employee -H "Content-Type: application/json"
```

```shell
curl http://localhost:8080/api/v1/employee/all
```

### Switch primary host in cluster
```shell
curl -i -X POST http://localhost:8080/api/v1/cluster/failover
```
