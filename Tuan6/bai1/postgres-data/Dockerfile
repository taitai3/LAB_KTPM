# Stage 1: pull image postgres gốc
# Stage 2: image mới có sẵn data (data ready)
FROM postgres:15-alpine

# Copy script khởi tạo data vào folder init của postgres
# Script này sẽ chạy tự động khi container khởi động lần đầu
COPY init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
