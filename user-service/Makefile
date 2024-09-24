run:
	./mvnw spring-boot:run

deps-clean-install:
	./mvnw dependency:purge-local-repository
	./mvnw clean install

deps-download:
	./mvnw dependency:resolve

dev:
	./mvnw spring-boot:run -X