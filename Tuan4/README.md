# Tuần 4 - Thao tác với Dockerfile

Thư mục này chứa 10 bài tập về Docker và Dockerfile.

## Danh sách bài tập

### Bài 1: Node.js Application
- **Thư mục:** `Bai1-NodeJS`
- **Mô tả:** Ứng dụng Node.js đơn giản hiển thị "Hello, Docker!" trên port 3000
- **Base image:** node:18
- **Build:** `docker build -t nodejs-app ./Bai1-NodeJS`
- **Run:** `docker run -p 3000:3000 nodejs-app`

### Bài 2: Python Flask Application
- **Thư mục:** `Bai2-Flask`
- **Mô tả:** Ứng dụng Flask hiển thị "Hello, Docker Flask!" trên port 5000
- **Base image:** python:3.9
- **Build:** `docker build -t flask-app ./Bai2-Flask`
- **Run:** `docker run -p 5000:5000 flask-app`

### Bài 3: React Application
- **Thư mục:** `Bai3-React`
- **Mô tả:** Ứng dụng React với build và chạy
- **Base image:** node:18-alpine
- **Build:** `docker build -t react-app ./Bai3-React`
- **Run:** `docker run -p 3000:3000 react-app`

### Bài 4: Static Website với Nginx
- **Thư mục:** `Bai4-Nginx`
- **Mô tả:** Trang web tĩnh được phục vụ bởi Nginx
- **Base image:** nginx:latest
- **Build:** `docker build -t nginx-app ./Bai4-Nginx`
- **Run:** `docker run -p 80:80 nginx-app`

### Bài 5: Go Application
- **Thư mục:** `Bai5-Go`
- **Mô tả:** Ứng dụng Go đơn giản
- **Base image:** golang:1.21-alpine
- **Build:** `docker build -t go-app ./Bai5-Go`
- **Run:** `docker run -p 8080:8080 go-app`

### Bài 6: Multi-stage Build
- **Thư mục:** `Bai6-MultiStage`
- **Mô tả:** Ứng dụng Node.js sử dụng multi-stage build
- **Base images:** node:18 (build), node:18-alpine (production)
- **Build:** `docker build -t multistage-app ./Bai6-MultiStage`
- **Run:** `docker run -p 3000:3000 multistage-app`

### Bài 7: Environment Variables
- **Thư mục:** `Bai7-EnvVar`
- **Mô tả:** Ứng dụng Python đọc biến môi trường APP_ENV
- **Base image:** python:3.9
- **Build:** `docker build -t env-app ./Bai7-EnvVar`
- **Run:** `docker run env-app`
- **Run với custom env:** `docker run -e APP_ENV=production env-app`

### Bài 8: PostgreSQL Custom
- **Thư mục:** `Bai8-PostgreSQL`
- **Mô tả:** PostgreSQL với script khởi tạo database tự động
- **Base image:** postgres:15
- **Build:** `docker build -t postgres-custom ./Bai8-PostgreSQL`
- **Run:** `docker run -p 5432:5432 postgres-custom`

### Bài 9: Redis với cấu hình tùy chỉnh
- **Thư mục:** `Bai9-Redis`
- **Mô tả:** Redis với file cấu hình tùy chỉnh
- **Base image:** redis:latest
- **Build:** `docker build -t redis-custom ./Bai9-Redis`
- **Run:** `docker run -p 6379:6379 redis-custom`

### Bài 10: PHP với Apache
- **Thư mục:** `Bai10-PHP-Apache`
- **Mô tả:** Ứng dụng PHP chạy trên Apache
- **Base image:** php:8.2-apache
- **Build:** `docker build -t php-app ./Bai10-PHP-Apache`
- **Run:** `docker run -p 80:80 php-app`

## Lệnh Docker cơ bản

```bash
# Build image
docker build -t <image-name> <path-to-dockerfile>

# Run container
docker run -p <host-port>:<container-port> <image-name>

# List running containers
docker ps

# Stop container
docker stop <container-id>

# Remove container
docker rm <container-id>

# List images
docker images

# Remove image
docker rmi <image-name>
```

## Ghi chú
- Tất cả các ứng dụng đều có thể build và chạy độc lập
- Mỗi bài tập có Dockerfile riêng và source code đầy đủ
- Có thể tùy chỉnh port và environment variables khi chạy container
