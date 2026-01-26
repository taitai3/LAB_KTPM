# Spring Boot Load Balancer Demo

Demo project với Spring Boot, Nginx Load Balancer, Docker và High Availability.

## Kiến trúc

```
Internet → Nginx Load Balancer → Spring Boot App Instances (3)
                ↓
        Health Checks & Failover
```

## Tính năng

- **Load Balancing**: Nginx phân phối requests đến 3 instances Spring Boot
- **High Availability**: Tự động failover khi instance bị down
- **Health Checks**: Monitoring và health checks cho tất cả services
- **Docker**: Containerized deployment
- **Scalability**: Dễ dàng thêm/bớt instances

## Cách chạy

### 1. Build và start tất cả services:

```bash
# Windows
./mvnw clean package -DskipTests
docker-compose up --build -d

# Hoặc dùng script (cần Git Bash trên Windows)
chmod +x build.sh
./build.sh
```

### 2. Test load balancer:

```bash
# Test basic
curl http://localhost/

# Test API endpoint
curl http://localhost/api/users

# Test multiple requests để thấy load balancing
for /l %i in (1,1,5) do curl http://localhost/ && echo.
```

### 3. Test high availability:

```bash
chmod +x test-availability.sh
./test-availability.sh
```

## Endpoints

- **Load Balancer**: http://localhost
- **Nginx Status**: http://localhost/status
- **App Instance 1**: http://localhost:8081
- **App Instance 2**: http://localhost:8082
- **App Instance 3**: http://localhost:8083

## API Endpoints

- `GET /` - Home page với instance info
- `GET /api/users` - Sample API endpoint
- `GET /health` - Health check endpoint
- `GET /actuator/health` - Spring Boot actuator health

## Monitoring

```bash
# Check container status
docker-compose ps

# View logs
docker-compose logs nginx
docker-compose logs app1

# Check nginx upstream status
curl http://localhost/status
```

## Load Balancing Strategy

- **Algorithm**: Least connections
- **Health Checks**: Mỗi 30s
- **Failover**: Tự động remove unhealthy instances
- **Recovery**: Tự động add lại khi instance healthy

## Cleanup

```bash
docker-compose down
docker system prune -f
```