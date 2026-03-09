# Sử dụng Redis latest làm base image
FROM redis:latest

# Copy file cấu hình Redis
COPY redis.conf /usr/local/etc/redis/redis.conf

# Expose port 6379
EXPOSE 6379

# Chạy Redis với file cấu hình tùy chỉnh
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]
