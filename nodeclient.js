"use strict";
const net = require('net');

const path = 'test.socket';

const socket = net.connect(path, () => {
  console.log("Connection established!");
});

socket.on('data', data => {
  console.log("Server: " + data.toString());
});

socket.on('end', () => {
  console.log("Connection closed!");
});
