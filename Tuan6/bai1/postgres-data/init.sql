-- Tạo bảng students
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT
);

-- Insert data sẵn vào image (data ready)
INSERT INTO students (name, email, age) VALUES
    ('Nguyen Van A', 'vana@iuh.edu.vn', 20),
    ('Tran Thi B', 'thib@iuh.edu.vn', 21),
    ('Le Van C', 'vanc@iuh.edu.vn', 22),
    ('Pham Thi D', 'thid@iuh.edu.vn', 20),
    ('Hoang Van E', 'vane@iuh.edu.vn', 23);
