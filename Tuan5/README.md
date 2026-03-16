# 📝 CMS Project — Lập trình theo kiến trúc

> Bài tập: CMS với 3 chức năng cơ bản nhất, theo 3 kiến trúc yêu cầu.

---

## 🏗️ Kiến trúc tổng quan

```
                    ┌─────────────────────────────┐
                    │        React Frontend        │
                    │   (PostForm / PostList)       │
                    └──────────────┬──────────────┘
                                   │ HTTP / REST API
                    ┌──────────────▼──────────────┐
                    │         Express Backend       │
                    │                              │
                    │  ┌─────────────────────────┐ │
                    │  │  Microkernel (Plugins)   │ │
                    │  │  ├── seo-plugin          │ │
                    │  │  └── audit-plugin        │ │
                    │  └─────────────────────────┘ │
                    │                              │
                    │  ┌─────────────────────────┐ │
                    │  │   Layered Architecture   │ │
                    │  │  Controller              │ │
                    │  │      ↓                   │ │
                    │  │  Service                 │ │
                    │  │      ↓                   │ │
                    │  │  Repository              │ │
                    │  │      ↓                   │ │
                    │  │  Model (Post)            │ │
                    │  └─────────────────────────┘ │
                    └──────────────────────────────┘
```

---

## 📁 Project Structure

```
cms-project/
├── docker-compose.yml          # 3. Docker Compose — chạy toàn bộ hệ thống
│
├── backend/
│   ├── Dockerfile
│   ├── package.json
│   ├── plugins/                # 2. Plugin System (Microkernel)
│   │   ├── seo-plugin/
│   │   │   └── index.js        # Auto-tạo slug, endpoint /api/posts/:id/seo
│   │   └── audit-plugin/
│   │       └── index.js        # Log tất cả write ops, endpoint /api/audit
│   └── src/
│       ├── index.js            # Entry point, khởi động app + plugins
│       ├── plugins/
│       │   └── PluginManager.js  # Microkernel core: register + boot plugins
│       └── (1. Layered Architecture)
│           ├── models/
│           │   └── Post.js     # Data shape
│           ├── repositories/
│           │   └── postRepository.js  # Data access (CRUD)
│           ├── services/
│           │   └── postService.js     # Business logic
│           ├── controllers/
│           │   └── postController.js  # HTTP handlers
│           └── routes/
│               └── postRoutes.js      # Route definitions
│
└── frontend/
    ├── Dockerfile
    ├── package.json
    └── src/
        ├── index.js
        ├── App.js              # Main page: load/create/delete posts
        ├── api/
        │   └── posts.js        # API calls (getPosts, createPost, deletePost)
        └── components/
            ├── PostForm.js     # Form tạo bài viết
            └── PostList.js     # Danh sách + nút xóa
```

---

## 🚀 Cách chạy

### Yêu cầu
- [Docker](https://www.docker.com/) + Docker Compose đã cài

### Chạy toàn bộ hệ thống

```bash
# Clone / copy project vào máy, rồi:
cd cms-project
docker-compose up --build
```

Sau khi build xong:
| Service   | URL                          |
|-----------|------------------------------|
| Frontend  | http://localhost:3000        |
| Backend   | http://localhost:4000        |
| API Posts | http://localhost:4000/api/posts |
| Audit Log | http://localhost:4000/api/audit |
| Health    | http://localhost:4000/api/health |

---

## 🔌 3 Phần theo yêu cầu

### 1. Code layer (Layered Architecture)

Mỗi lớp chỉ giao tiếp với lớp ngay bên dưới:

| Layer        | File                          | Nhiệm vụ                        |
|--------------|-------------------------------|----------------------------------|
| Controller   | `controllers/postController.js` | Nhận HTTP request, trả response |
| Service      | `services/postService.js`     | Business logic, validation       |
| Repository   | `repositories/postRepository.js` | CRUD data                     |
| Model        | `models/Post.js`              | Định nghĩa cấu trúc Post         |

### 2. Code Plugin (Microkernel)

`PluginManager` là kernel. Mỗi plugin chỉ cần implement:
```js
module.exports = {
  name: "ten-plugin",
  boot(app) {
    // thêm middleware, route, logic... vào app
  }
}
```

Plugin hiện có:
- **seo-plugin**: tự tạo `slug` khi tạo bài, endpoint `/api/posts/:id/seo`
- **audit-plugin**: log mọi POST/PUT/DELETE, endpoint `/api/audit`

Thêm plugin mới → tạo file → `pluginManager.register(yourPlugin)` trong `index.js`. Không cần sửa code gốc.

### 3. Docker Compose

```bash
docker-compose up --build   # build và chạy
docker-compose down         # dừng
docker-compose logs -f      # xem logs
```

---

## 🛠️ API Endpoints

| Method | Endpoint                  | Mô tả                   |
|--------|---------------------------|--------------------------|
| GET    | /api/posts                | Lấy tất cả bài viết      |
| GET    | /api/posts/:id            | Lấy 1 bài viết           |
| POST   | /api/posts                | Tạo bài viết mới         |
| PUT    | /api/posts/:id            | Cập nhật bài viết        |
| DELETE | /api/posts/:id            | Xóa bài viết             |
| GET    | /api/posts/:id/seo        | SEO info (plugin)        |
| GET    | /api/audit                | Audit log (plugin)       |
| GET    | /api/health               | Health check + plugins   |

---

## 📦 Test nhanh bằng curl

```bash
# Tạo bài viết
curl -X POST http://localhost:4000/api/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"Hello CMS","content":"Nội dung đầu tiên","author":"Nam"}'

# Lấy tất cả
curl http://localhost:4000/api/posts

# Xem audit log
curl http://localhost:4000/api/audit
```
