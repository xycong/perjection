var express = require('express');
var app = express();

var cors = require('cors');
var bodyParser = require('body-parser');
var logger = require('morgan');
var http = require('http');
var server = http.createServer(app);
var db = require('./modules/db');
var mongoURL = "mongodb://127.0.0.1/perjection";

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());

db.connect(mongoURL, function (err) {
    if (err) {
        console.log(err);
        process.exit(1);
    }

    console.log('Connected to database');
    app.use('/api', require('./controllers/api'));

    var port = process.env.PORT || 3000;
    var listener = server.listen(port, function () {
        console.log('Listening on port ' + server.address().port);
    });
});

module.exports = app;