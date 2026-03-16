// SEO Plugin
// Adds a /api/posts/:id/seo endpoint that generates a slug + meta description

const postRepository = require("../../src/repositories/postRepository");

const seoPlugin = {
  name: "seo-plugin",

  boot(app) {
    // Intercept POST /api/posts to auto-generate slug
    app.use((req, res, next) => {
      if (req.method === "POST" && req.path === "/api/posts") {
        const title = req.body?.title || "";
        req.body.slug = title
          .toLowerCase()
          .replace(/[^a-z0-9\s]/g, "")
          .trim()
          .replace(/\s+/g, "-");
      }
      next();
    });

    // Extra endpoint: get SEO info for a post
    app.get("/api/posts/:id/seo", (req, res) => {
      const post = postRepository.findById(req.params.id);
      if (!post) return res.status(404).json({ error: "Post not found" });

      res.json({
        slug: post.slug,
        metaTitle: `${post.title} | My CMS`,
        metaDescription: post.content.substring(0, 160),
        canonical: `https://example.com/posts/${post.slug}`,
      });
    });
  },
};

module.exports = seoPlugin;
