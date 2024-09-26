.PHONY: start
start:
	./mvnw spring-boot:run

.PHONY: stop
stop:
	curl -X POST localhost:8080/actuator/shutdown
