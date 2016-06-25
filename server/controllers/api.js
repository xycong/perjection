var express = require('express');
var router = express.Router();
var Coord = require('../models/Coord');

router.get('/', function (req, res) {
    Recipe.findRecipes(req.body).then(function (docs) {
        res.send({ recipes: docs });
    }, function (err) {
        res.send(err);
    });
});

router.get('/:id', function (req, res) {
    Recipe.findRecipe(req.params.id).then(function (docs) {
        res.send({ recipe: docs });
    }, function (err) {
        res.send(err);
    });
});

router.post('/:id/save', function (req, res) {
    Recipe.saveRecipe(req.params.id).then(function (docs) {
        res.send(docs);
    }, function(err) {
        res.send(err);
    });
});