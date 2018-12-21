"use strict";

const registers = [0, 0, 0, 0, 0, 0];

const addr = (a, b, c) => registers[c] = registers[a] + registers[b];
const addi = (a, b, c) => registers[c] = registers[a] + b;
const mulr = (a, b, c) => registers[c] = registers[a] * registers[b];
const muli = (a, b, c) => registers[c] = registers[a] * b;
const banr = (a, b, c) => registers[c] = registers[a] & registers[b];
const bani = (a, b, c) => registers[c] = registers[a] & b;
const borr = (a, b, c) => registers[c] = registers[a] | registers[b];
const bori = (a, b, c) => registers[c] = registers[a] | b;
const setr = (a, b, c) => registers[c] = registers[a];
const seti = (a, b, c) => registers[c] = a;
const gtir = (a, b, c) => registers[c] = a > registers[b] ? 1 : 0;
const gtri = (a, b, c) => registers[c] = registers[a] > b ? 1 : 0;
const gtrr = (a, b, c) => registers[c] = registers[a] > registers[b] ? 1 : 0;
const eqir = (a, b, c) => registers[c] = a === registers[b] ? 1 : 0;
const eqri = (a, b, c) => registers[c] = registers[a] === b ? 1 : 0;
const eqrr = (a, b, c) => registers[c] = registers[a] === registers[b] ? 1 : 0;

const operations = [addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr];

const fs = require("fs");
const lines = fs.readFileSync("day19/js/19.in").toString().split("\r\n");

const ip = Number(lines[0].substring(4));
const instructions = lines.slice(1).map(s => s.split(" "))
    .map(arr => {
        return [Array.from(operations.values()).filter(fn => fn.name === arr[0])[0], Number(arr[1]), Number(arr[2]), Number(arr[3])];
    });

while (registers[ip] < instructions.length) {
    const [operation, ...args] = instructions[registers[ip]];
    operation.apply(null, args);
    registers[ip]++;
}

console.log("Part one: " + registers[0]);
