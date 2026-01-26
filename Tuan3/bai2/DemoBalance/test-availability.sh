#!/bin/bash

echo "Testing High Availability..."

echo "1. Normal load balancing test:"
for i in {1..6}; do
  echo "Request $i:"
  curl -s http://localhost/ | jq -r '.instance + " - " + .hostname'
done

echo -e "\n2. Stopping one instance to test failover..."
docker stop demo-app-2

echo "Waiting 10 seconds for health checks..."
sleep 10

echo "Testing with one instance down:"
for i in {1..4}; do
  echo "Request $i:"
  curl -s http://localhost/ | jq -r '.instance + " - " + .hostname'
done

echo -e "\n3. Restarting stopped instance..."
docker start demo-app-2

echo "Waiting 30 seconds for instance to be healthy..."
sleep 30

echo "Testing after recovery:"
for i in {1..4}; do
  echo "Request $i:"
  curl -s http://localhost/ | jq -r '.instance + " - " + .hostname'
done

echo -e "\nHigh availability test completed!"