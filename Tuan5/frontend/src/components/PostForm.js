import React, { useState } from "react";

export default function PostForm({ onCreated }) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [author, setAuthor] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!title || !content) return alert("Title và Content không được rỗng!");
    setLoading(true);
    try {
      await onCreated({ title, content, author });
      setTitle("");
      setContent("");
      setAuthor("");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form}>
      <h2>Tạo bài viết mới</h2>
      <input
        placeholder="Tiêu đề *"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        style={styles.input}
      />
      <input
        placeholder="Tác giả"
        value={author}
        onChange={(e) => setAuthor(e.target.value)}
        style={styles.input}
      />
      <textarea
        placeholder="Nội dung *"
        value={content}
        onChange={(e) => setContent(e.target.value)}
        rows={4}
        style={{ ...styles.input, resize: "vertical" }}
      />
      <button type="submit" disabled={loading} style={styles.button}>
        {loading ? "Đang tạo..." : "Tạo bài viết"}
      </button>
    </form>
  );
}

const styles = {
  form: { display: "flex", flexDirection: "column", gap: 10, marginBottom: 30 },
  input: { padding: "10px 14px", fontSize: 15, borderRadius: 6, border: "1px solid #ccc" },
  button: { padding: "10px 20px", background: "#2563eb", color: "#fff", border: "none", borderRadius: 6, cursor: "pointer", fontSize: 15 },
};
