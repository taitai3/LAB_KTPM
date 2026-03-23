-- ============================================================
-- Database Partitioning Demo - MariaDB
-- ============================================================

USE userdb;

-- ── Horizontal Partitioning ──────────────────────────────────
-- Chia dữ liệu theo hàng (row), dựa vào điều kiện id
-- table_user_01: user có id lẻ (1, 3, 5...)
-- table_user_02: user có id chẵn (2, 4, 6...)

CREATE TABLE IF NOT EXISTS table_user_01 (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    age INT
);

CREATE TABLE IF NOT EXISTS table_user_02 (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    age INT
);

-- Insert data vào table_user_01 (id lẻ)
INSERT INTO table_user_01 VALUES (1, 'Nguyen Van A', 'vana@iuh.edu.vn', 20);
INSERT INTO table_user_01 VALUES (3, 'Le Van C', 'vanc@iuh.edu.vn', 22);
INSERT INTO table_user_01 VALUES (5, 'Hoang Van E', 'vane@iuh.edu.vn', 23);

-- Insert data vào table_user_02 (id chẵn)
INSERT INTO table_user_02 VALUES (2, 'Tran Thi B', 'thib@iuh.edu.vn', 21);
INSERT INTO table_user_02 VALUES (4, 'Pham Thi D', 'thid@iuh.edu.vn', 20);
INSERT INTO table_user_02 VALUES (6, 'Vo Thi F', 'thif@iuh.edu.vn', 24);

-- ── Vertical Partitioning ────────────────────────────────────
-- Chia dữ liệu theo cột, tách thông tin cơ bản và thông tin chi tiết

CREATE TABLE IF NOT EXISTS user_basic (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS user_detail (
    id INT PRIMARY KEY,
    age INT,
    address VARCHAR(200),
    phone VARCHAR(20)
);

INSERT INTO user_basic VALUES (1, 'Nguyen Van A', 'vana@iuh.edu.vn');
INSERT INTO user_basic VALUES (2, 'Tran Thi B', 'thib@iuh.edu.vn');

INSERT INTO user_detail VALUES (1, 20, 'Ho Chi Minh', '0901234567');
INSERT INTO user_detail VALUES (2, 21, 'Ha Noi', '0912345678');
