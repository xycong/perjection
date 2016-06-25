var db = require('../modules/db');
var collection = db.get().collection('positions');

exports.getPositions = function (position, callback) {
    collection.find().toArray(function (err, docs) {
        callback(err, docs);
    });
}

exports.savePosition = function(position) {
    console.log(position);
    collection.save(position);
}