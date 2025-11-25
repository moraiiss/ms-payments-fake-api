build:
	docker-compose up --build -d

up:
	docker-compose up -d

test:
	./gradlew test

check:
	./gradlew checkstyleMain checkstyleTest --daemon
