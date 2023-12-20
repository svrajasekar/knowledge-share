const express = require("express");
const router = express.Router();
const productCategory = require("../models/ProductCategory");

router.get("/", async(req, res) => {
    try {
        const data = await productCategory.find()
        res.status(200).send(data);
    } catch( err ) {
        res.status(500).send(err);
    }
});

module.exports = router;