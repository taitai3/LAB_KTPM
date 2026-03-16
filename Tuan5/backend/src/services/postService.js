// Service Layer: contains business logic
// Controllers call services; services call repositories.

const postRepository = require("../repositories/postRepository");

class PostService {
  getAllPosts() {
    return postRepository.findAll();
  }

  getPostById(id) {
    const post = postRepository.findById(id);
    if (!post) throw new Error(`Post not found: ${id}`);
    return post;
  }

  createPost(data) {
    if (!data.title || !data.content) {
      throw new Error("Title and content are required");
    }
    return postRepository.create(data);
  }

  updatePost(id, data) {
    const post = postRepository.update(id, data);
    if (!post) throw new Error(`Post not found: ${id}`);
    return post;
  }

  deletePost(id) {
    const deleted = postRepository.delete(id);
    if (!deleted) throw new Error(`Post not found: ${id}`);
    return true;
  }
}

module.exports = new PostService();
