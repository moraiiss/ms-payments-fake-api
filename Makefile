-include .env
export $(shell test -f .env && sed 's/=.*//' .env)

build:
	docker-compose up --build -d

up:
	docker-compose up -d

down:
	docker-compose down

test:
	./gradlew test

check:
	./gradlew checkstyleMain checkstyleTest

start:
	./gradlew bootRun

builder:
	./gradlew clean build

restart: down builder up start

init: builder up start