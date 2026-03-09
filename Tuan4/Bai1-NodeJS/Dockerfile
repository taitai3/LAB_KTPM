# Sử dụng Node.js 18 làm base image
FROM node:18

# Tạo thư mục làm việc
WORKDIR /app

# Copy package files
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Copy source code
COPY . .

# Expose port 3000
EXPOSE 3000

# Chạy ứng dụng
CMD ["node", "app.js"]
