"use strict";

const fs = require("fs");
const lines1 = fs.readFileSync("16_1.in").toString().split("\r\n");
const samples = [];

for (let i = 0; i < lines1.length; i++) {
    samples.push({
        before: lines1[i++].substring(9, 19).split(", ").map(Number),
        instruction: lines1[i++].split(" ").map(Number),
        after: lines1[i++].substring(9, 19).split(", ").map(Number),
    });
}

let registers = [0, 0, 0, 0];

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

const instructions = [addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr];

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
    instructions.forEach((instruction) => {
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

const samples_possibilities = new Map(samples.map(sample => {
    let possibilites = new Set();
    instructions.forEach((instruction) => {
        registers = sample.before.slice();
        instruction.apply(null, sample.instruction.slice(1));

        if (arrayEquals(registers, sample.after)) {
            possibilites.add(instruction);
        }
    });

    return [sample, possibilites];
}));

const opcodes = new Map();

while(opcodes.size < 16) {
    let candidates = Array.from(samples_possibilities.entries()).filter(arr => arr[1].size === 1);

    candidates.forEach(candidate => {
        let [sample, possibilites] = candidate;
        let opcode = sample.instruction[0];
        let instruction = possibilites.values().next().value;

        if(!opcodes.has(opcode)) {
            opcodes.set(opcode, instruction);

            Array.from(samples_possibilities.values()).forEach((value) => {
                if(value.has(instruction)) {
                    value.delete(instruction);
                }
            });
        }
    })
}

registers = [0, 0, 0, 0];

const lines2 = fs.readFileSync("16_2.in").toString().split("\r\n");

lines2.forEach(line => {
    let instruction = line.split(" ").map(Number);
    let operation = opcodes.get(instruction[0]);
    operation.apply(null, instruction.slice(1));
});

console.log("Part two: " + registers[0]);