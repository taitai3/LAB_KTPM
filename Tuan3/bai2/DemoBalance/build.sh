#!/bin/bash

echo "Building Spring Boot application..."
./mvnw clean package -DskipTests

echo "Building and starting Docker containers..."
docker-compose up --build -d

echo "Waiting for services to be ready..."
sleep 30

echo "Checking service health..."
echo "Nginx Load Balancer: http://localhost"
echo "App Instance 1: http://localhost:8081"
echo "App Instance 2: http://localhost:8082" 
echo "App Instance 3: http://localhost:8083"

echo "Testing load balancer..."
for i in {1..5}; do
  echo "Request $i:"
  curl -s http://localhost/ | jq .
  echo ""
done