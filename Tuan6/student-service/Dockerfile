# ============================================================
# Multi-Stage Build - Docker Tuan 6
# ============================================================

# ── Stage 1: BUILD ──────────────────────────────────────────
# Môi trường chỉ cần lúc tạo image (Maven + JDK)
# Sau khi build xong, stage này bị loại bỏ --> image nhỏ hơn
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml trước để cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source và build
COPY src ./src
RUN mvn clean package -DskipTests

# ── Stage 2: RUN ────────────────────────────────────────────
# Chỉ cần JRE để chạy, không cần Maven nữa
# --> khi chạy container --> image nhỏ gọn, bảo mật hơn
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Chỉ copy file JAR từ stage build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
