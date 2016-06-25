var express = require('express');
var router = express.Router();
var Position = require('../models/Position');

router.get('/', function (req, res) {
    Position.getPositions(req.position, function (docs) {
        res.send({ position: docs });
    }, function (err) {
        res.send(err);
    });
});

router.post('/save', function (req, res) {
    console.log(req);

    Position.savePosition(req.body, function (docs) {
    }, function(err) {
        res.send(err);
    });
});

module.exports = router;