"use strict";

const fs = require("fs");
const lines = fs.readFileSync("16_1.in").toString().split("\r\n");
const samples = [];

for (let i = 0; i < lines.length; i++) {
    samples.push({
        before: lines[i++].substring(9, 19).split(", ").map(Number),
        instruction: lines[i++].split(" ").map(Number),
        after: lines[i++].substring(9, 19).split(", ").map(Number),
    });
}

let registers = [0, 0, 0, 0];

const instructions = new Map([
    // Addition
    ["addr", (a, b, c) => registers[c] = registers[a] + registers[b]],
    ["addi", (a, b, c) => registers[c] = registers[a] + b],

    // Multiplication
    ["mulr", (a, b, c) => registers[c] = registers[a] * registers[b]],
    ["muli", (a, b, c) => registers[c] = registers[a] * b],

    // Bitwise AND
    ["banr", (a, b, c) => registers[c] = registers[a] & registers[b]],
    ["bani", (a, b, c) => registers[c] = registers[a] & b],

    // Bitwise OR
    ["borr", (a, b, c) => registers[c] = registers[a] | registers[b]],
    ["bori", (a, b, c) => registers[c] = registers[a] | b],

    // Assignment
    ["setr", (a, b, c) => registers[c] = registers[a]],
    ["seti", (a, b, c) => registers[c] = a],

    // Greater-than testing
    ["gtir", (a, b, c) => registers[c] = a > registers[b] ? 1 : 0],
    ["gtri", (a, b, c) => registers[c] = registers[a] > b ? 1 : 0],
    ["gtrr", (a, b, c) => registers[c] = registers[a] > registers[b] ? 1 : 0],

    // Equality testing
    ["eqir", (a, b, c) => registers[c] = a === registers[b] ? 1 : 0],
    ["eqri", (a, b, c) => registers[c] = registers[a] === b ? 1 : 0],
    ["eqrr", (a, b, c) => registers[c] = registers[a] === registers[b] ? 1 : 0]
]);

const arrayEquals = (arr1, arr2) => {
    if (arr1.length !== arr2.length) {
        return false;
    }

    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }

    return true;
};

let total = 0;

samples.forEach(sample => {
    let count = 0;
    instructions.forEach((instruction, name) => {
        registers = sample.before.slice();
        instruction.apply(null, sample.instruction.slice(1));

        if (arrayEquals(registers, sample.after)) {
            count++;
        }
    });

    if (count >= 3) {
        total++;
    }
});

console.log("Part one: " + total);