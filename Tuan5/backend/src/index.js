const express = require("express");
const cors = require("cors");
const postRoutes = require("./routes/postRoutes");
const PluginManager = require("./plugins/PluginManager");
const seoPlugin = require("../plugins/seo-plugin");
const auditPlugin = require("../plugins/audit-plugin");

const app = express();
const PORT = process.env.PORT || 4000;

// ── Middleware ────────────────────────────────────────────────
app.use(cors());
app.use(express.json());

// ── Plugin System (Microkernel) ───────────────────────────────
const pluginManager = new PluginManager(app);
pluginManager.register(seoPlugin);
pluginManager.register(auditPlugin);
pluginManager.bootAll();

// ── Routes (Layer) ────────────────────────────────────────────
app.use("/api/posts", postRoutes);

// ── Health check ──────────────────────────────────────────────
app.get("/api/health", (req, res) => {
  res.json({
    status: "ok",
    plugins: pluginManager.list(),
  });
});

app.listen(PORT, () => {
  console.log(`CMS Backend running on http://localhost:${PORT}`);
  console.log(`Loaded plugins: ${pluginManager.list().join(", ")}`);
});
