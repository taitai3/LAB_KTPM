import React, { useEffect, useState } from "react";
import PostForm from "./components/PostForm";
import PostList from "./components/PostList";
import { getPosts, createPost, deletePost } from "./api/posts";

export default function App() {
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);

  const loadPosts = async () => {
    try {
      const data = await getPosts();
      setPosts(data);
    } catch (e) {
      setError("Không kết nối được backend. Hãy chạy docker-compose up.");
    }
  };

  useEffect(() => {
    loadPosts();
  }, []);

  const handleCreate = async (data) => {
    await createPost(data);
    await loadPosts();
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Xóa bài viết này?")) return;
    await deletePost(id);
    await loadPosts();
  };

  return (
    <div style={styles.wrapper}>
      <header style={styles.header}>
        <h1 style={styles.headerTitle}>📝 My CMS</h1>
        <span style={styles.badge}>Microkernel + Layered Architecture</span>
      </header>

      <main style={styles.main}>
        {error && <div style={styles.error}>{error}</div>}
        <PostForm onCreated={handleCreate} />
        <hr style={{ margin: "20px 0", borderColor: "#e2e8f0" }} />
        <PostList posts={posts} onDelete={handleDelete} />
      </main>
    </div>
  );
}

const styles = {
  wrapper: { fontFamily: "Segoe UI, sans-serif", maxWidth: 760, margin: "0 auto", padding: "0 20px 60px" },
  header: { background: "#1e293b", color: "#fff", padding: "20px 30px", borderRadius: "0 0 12px 12px", marginBottom: 30, display: "flex", justifyContent: "space-between", alignItems: "center" },
  headerTitle: { margin: 0, fontSize: 24 },
  badge: { background: "#2563eb", padding: "4px 12px", borderRadius: 20, fontSize: 12 },
  main: { background: "#fff", padding: 30, borderRadius: 12, boxShadow: "0 2px 12px rgba(0,0,0,0.08)" },
  error: { background: "#fef2f2", border: "1px solid #fca5a5", color: "#dc2626", padding: "12px 16px", borderRadius: 8, marginBottom: 20 },
};
