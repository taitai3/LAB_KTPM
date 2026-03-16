// PluginManager = Microkernel Core
// Each plugin is an object with: { name, boot(app) }
// The kernel just registers and boots plugins — plugins extend the system.

class PluginManager {
  constructor(app) {
    this.app = app;
    this.plugins = new Map();
  }

  // Register a plugin (does not boot it yet)
  register(plugin) {
    if (!plugin.name || typeof plugin.boot !== "function") {
      throw new Error("Invalid plugin: must have { name, boot(app) }");
    }
    this.plugins.set(plugin.name, plugin);
    console.log(`[PluginManager] Registered plugin: ${plugin.name}`);
  }

  // Boot all registered plugins
  bootAll() {
    for (const [name, plugin] of this.plugins) {
      plugin.boot(this.app);
      console.log(`[PluginManager] Booted plugin: ${name}`);
    }
  }

  // List plugin names
  list() {
    return Array.from(this.plugins.keys());
  }
}

module.exports = PluginManager;
