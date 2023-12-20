const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const productCategorySchema = new Schema({
    _id: mongoose.Types.ObjectId,
    category_name: {type: String, required: true, trim: true, maxLength: 100},
    description: {type: String, required: false, maxLength: 250},
    tax_id: mongoose.Types.ObjectId
});

module.exports = mongoose.model("ProductCategory", productCategorySchema);
