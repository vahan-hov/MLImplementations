var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.json([
    { img: "/vergil.jpg"},
    { img: "/dante.jpg"}
  ]);
});

module.exports = router;
