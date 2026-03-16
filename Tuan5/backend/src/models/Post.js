// Model layer: defines the shape of a Post
// In production, replace with a DB model (Mongoose, Sequelize, etc.)

class Post {
  constructor({ id, title, content, author, createdAt, slug, auditLog }) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author || "Anonymous";
    this.createdAt = createdAt || new Date().toISOString();
    this.slug = slug || null;       // filled by SEO plugin
    this.auditLog = auditLog || []; // filled by Audit plugin
  }
}

module.exports = Post;
