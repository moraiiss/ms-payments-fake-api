# build
FROM gradle:8.5-jdk21 AS build

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/ gradle/

# cache das dependências
RUN --mount=type=cache,target=/root/.gradle/caches gradle dependencies

COPY src src
COPY config config

# compila o código fonte para empacotar o jar
# remove os testes no build
RUN --mount=type=cache,target=/root/.gradle/caches gradle bootJar -x test -x checkstyleMain -x checkstyleTest

# runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# copia o JAR compilado do estágio de build
COPY --from=build /app/build/libs/*.jar ./app.jar

COPY docker-entrypoint.sh ./

RUN adduser -D -H spring && chmod +x ./docker-entrypoint.sh

USER spring

ENTRYPOINT ["/app/docker-entrypoint.sh"]

