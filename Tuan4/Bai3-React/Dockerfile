# Sử dụng Node.js 18 Alpine làm base image
FROM node:18-alpine

# Tạo thư mục làm việc
WORKDIR /app

# Copy package files
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Copy source code
COPY . .

# Build ứng dụng React
RUN npm run build

# Expose port 3000
EXPOSE 3000

# Chạy ứng dụng
CMD ["npm", "start"]
