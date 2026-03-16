import React from "react";

export default function PostList({ posts, onDelete }) {
  if (posts.length === 0) return <p style={{ color: "#888" }}>Chưa có bài viết nào.</p>;

  return (
    <div>
      <h2>Danh sách bài viết ({posts.length})</h2>
      {posts.map((post) => (
        <div key={post.id} style={styles.card}>
          <div style={styles.cardHeader}>
            <div>
              <h3 style={styles.title}>{post.title}</h3>
              <small style={styles.meta}>
                {post.author} · {new Date(post.createdAt).toLocaleString("vi-VN")}
                {post.slug && <span style={styles.slug}> · /{post.slug}</span>}
              </small>
            </div>
            <button onClick={() => onDelete(post.id)} style={styles.deleteBtn}>
              Xóa
            </button>
          </div>
          <p style={styles.content}>{post.content}</p>
        </div>
      ))}
    </div>
  );
}

const styles = {
  card: { background: "#f8fafc", border: "1px solid #e2e8f0", borderRadius: 8, padding: "16px 20px", marginBottom: 14 },
  cardHeader: { display: "flex", justifyContent: "space-between", alignItems: "flex-start", marginBottom: 8 },
  title: { margin: 0, fontSize: 18, color: "#1e293b" },
  meta: { color: "#64748b", fontSize: 13 },
  slug: { color: "#2563eb" },
  content: { margin: 0, color: "#475569", lineHeight: 1.6 },
  deleteBtn: { background: "#ef4444", color: "#fff", border: "none", borderRadius: 6, padding: "6px 14px", cursor: "pointer", fontSize: 13 },
};
