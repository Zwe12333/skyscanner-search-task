
# Skyscanner Search Task — Solution Scaffold

This repository contains a Dropwizard-style microservice implementing a POST `/search` endpoint.

## How to run
1. Install Java 17+ and Maven
2. Run:
```
mvn package
java -jar target/search-service-1.0-SNAPSHOT.jar server
```

## How to test
```
curl -X POST http://localhost:8080/search -H "Content-Type: application/json" -d '{"city":"petalborough"}'
```

## How to upload to GitHub (Baby Steps)
1. Go to GitHub → New Repository  
2. Name it: `skyscanner-search-task`  
3. Do NOT add a README  
4. Click *Create*  
5. Click **Upload files**  
6. Drag and drop ALL files from inside this ZIP  
7. Click **Commit changes**
