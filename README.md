# selenium-ci

### Requirements

- Docker Desktop
- Docker compose
- Java 17

### Docker composer with selenium-hub

```
docker-compose up -d
```

### Launch test

```
./gradlew test -Denv=local

./gradlew test -Denv=jenkins
```