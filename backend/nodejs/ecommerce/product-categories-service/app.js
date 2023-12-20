const express = require("express");
const cors = require("cors");
const dotenv = require("dotenv").config();

const app = express();

app.use(express.json());
app.use(cors());
app.use(dotenv);

const port = process.env.PORT || 3000;


app.listen(port, () => {
    console.log(`API Server Listening on ${port}`);
})