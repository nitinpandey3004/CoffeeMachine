# CoffeeMachine

## Prerequisites
- Java 15 (please check `Build and Run Using Docker` if you cannot install java 15 locally)

## Build
```shell
mvn clean
mvn package
```

## Run

### Application
```shell
java -jar target/demo-1.0.0-SNAPSHOT.jar
```

### Tests
```shell
mvn test
```


## Build and Run Using Docker

### Build
```shell
docker build -t coffeemachine .
docker build -t coffeemachinetest -f Dockerfile.test .
```

### Run Application
```shell
docker run coffeemachine
```

### Run Tests
```shell
docker run coffeemachinetest
```