// Audit Plugin
// Logs every write operation (POST, PUT, DELETE) to an in-memory audit log
// and exposes a /api/audit endpoint to view it.

const auditLog = [];

const auditPlugin = {
  name: "audit-plugin",

  boot(app) {
    // Middleware: record every mutating request
    app.use((req, res, next) => {
      if (["POST", "PUT", "DELETE"].includes(req.method)) {
        const entry = {
          timestamp: new Date().toISOString(),
          method: req.method,
          path: req.path,
          body: req.body,
        };
        auditLog.push(entry);
      }
      next();
    });

    // Endpoint: view audit log
    app.get("/api/audit", (req, res) => {
      res.json(auditLog);
    });
  },
};

module.exports = auditPlugin;
