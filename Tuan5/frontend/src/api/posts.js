// api/posts.js — all API calls go through here (thin wrapper around fetch)

const BASE = "/api";

export const getPosts = () =>
  fetch(`${BASE}/posts`).then((r) => r.json());

export const createPost = (data) =>
  fetch(`${BASE}/posts`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  }).then((r) => r.json());

export const deletePost = (id) =>
  fetch(`${BASE}/posts/${id}`, { method: "DELETE" }).then((r) => r.json());
