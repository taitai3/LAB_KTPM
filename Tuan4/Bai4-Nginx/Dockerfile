# Sử dụng Nginx latest làm base image
FROM nginx:latest

# Copy file HTML vào thư mục mặc định của Nginx
COPY index.html /usr/share/nginx/html/

# Expose port 80
EXPOSE 80

# Nginx tự động chạy khi container start
CMD ["nginx", "-g", "daemon off;"]
