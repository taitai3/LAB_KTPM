const express = require("express");
const router = express.Router();
const postController = require("../controllers/postController");

router.get("/", postController.getAll.bind(postController));
router.get("/:id", postController.getById.bind(postController));
router.post("/", postController.create.bind(postController));
router.put("/:id", postController.update.bind(postController));
router.delete("/:id", postController.delete.bind(postController));

module.exports = router;
