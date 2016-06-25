var express = require('express');
var router = express.Router();
var Position = require('../models/Position');

router.get('/', function (req, res) {
    var position = new Object();
    console.log("Query");
    position.lat = req.query.position.lat;
    position.lng = req.query.position.lng;
    console.log(position);
    if (position != undefined) {
        Position.getPositions(position, function (docs) {
            res.send({ docs });
        }, function (err) {
            res.send(err);
        });
    }
});

router.post('/save', function (req, res) {
    console.log(req.body);
    Position.savePosition(req.body, function (docs) {
        res.end();
    }, function (err) {
        res.send(err);
    });
});

module.exports = router;