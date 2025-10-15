# Kapcsolat Spring Boot Alkalmazás

Ez a projekt egy Spring Boot alapú backend alkalmazás PostgreSQL adatbázissal. Az alábbiakban található a fordítási és futtatási útmutató.

## Szükséges eszközök

- Java 17+ vagy újabb
- Maven 3.8+
- Docker & Docker Compose

## Docker konténer indítása

1. Nyisd meg a terminált a projekt gyökérkönyvtárában, ahol a `docker-compose.yaml` található.
2. Indítsd el a PostgreSQL konténert:


```bash
- docker-compose up -d
```


## Postgre konténer részlerek 
- Felhasználó: postgres
- Jelszó: postgres
- Adatbázis: kapcsolat
- Port: 5432

## Spring Boot alkalmazás fordítása 
- mvn clean install

## Spring Boot alkalmazás futattása
```bash
- mvn spring-boot:run
```


## Vagy fájl futtatása
```bash
- java -jar target/exercises-backend-0.0.1-SNAPSHOT.jar
```

## Docker konténer leállítása
```bash
- docker-compose down
```
