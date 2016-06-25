var db = require('../modules/db');
var collection = db.get().collection('positions');

exports.getPositions = function (position, callback) {
    collection.find().toArray(function (err, docs) {
        var range = [];
        for(var i = 0; i < docs.length; i++) {
            if(getDistanceFromLatLonInKm(docs[i].lat, docs[i].lng, position.lat, position.lng) < 0.7) {
                range.push(docs[i]);
            }
        }
        callback(err, range);
    });
}

exports.savePosition = function(position) {
    collection.save(position);
}

function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
  var R = 6371; // Radius of the earth in km
  var dLat = deg2rad(lat2-lat1);  // deg2rad below
  var dLon = deg2rad(lon2-lon1); 
  var a = 
    Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
    Math.sin(dLon/2) * Math.sin(dLon/2)
    ; 
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  var d = R * c; // Distance in km
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180)
}