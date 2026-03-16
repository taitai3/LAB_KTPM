// Controller Layer: handles HTTP requests/responses
// No business logic here — delegates everything to the service.

const postService = require("../services/postService");

class PostController {
  getAll(req, res) {
    try {
      const posts = postService.getAllPosts();
      res.json(posts);
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  }

  getById(req, res) {
    try {
      const post = postService.getPostById(req.params.id);
      res.json(post);
    } catch (err) {
      res.status(404).json({ error: err.message });
    }
  }

  create(req, res) {
    try {
      const post = postService.createPost(req.body);
      res.status(201).json(post);
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  }

  update(req, res) {
    try {
      const post = postService.updatePost(req.params.id, req.body);
      res.json(post);
    } catch (err) {
      res.status(404).json({ error: err.message });
    }
  }

  delete(req, res) {
    try {
      postService.deletePost(req.params.id);
      res.json({ message: "Post deleted" });
    } catch (err) {
      res.status(404).json({ error: err.message });
    }
  }
}

module.exports = new PostController();
