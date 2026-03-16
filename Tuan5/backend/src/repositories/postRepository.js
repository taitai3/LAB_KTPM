// Repository Layer: handles all data access (CRUD)
// Swap out the in-memory store with a real DB here without touching other layers.

const { v4: uuidv4 } = require("uuid");
const Post = require("../models/Post");

// In-memory store
let posts = [];

class PostRepository {
  findAll() {
    return posts;
  }

  findById(id) {
    return posts.find((p) => p.id === id) || null;
  }

  create(data) {
    const post = new Post({ id: uuidv4(), ...data });
    posts.push(post);
    return post;
  }

  update(id, data) {
    const index = posts.findIndex((p) => p.id === id);
    if (index === -1) return null;
    posts[index] = new Post({ ...posts[index], ...data });
    return posts[index];
  }

  delete(id) {
    const index = posts.findIndex((p) => p.id === id);
    if (index === -1) return false;
    posts.splice(index, 1);
    return true;
  }
}

module.exports = new PostRepository();
