"use strict";

const fs = require("fs");
const board = fs.readFileSync("day18/js/18.in").toString().split("\r\n").map(s => s.split(""));

const adjacent = (board, x, y) => {
    const arr = [];

    // Top
    if (y > 0) {
        arr.push({ x: x, y: y - 1, type: board[y - 1][x] });
    }

    // Top Right
    if (y > 0 && x < board[y - 1].length - 1) {
        arr.push({ x: x + 1, y: y - 1, type: board[y - 1][x + 1] });
    }

    // Right
    if (x < board[y].length - 1) {
        arr.push({ x: x + 1, y: y, type: board[y][x + 1] });
    }

    // Bottom Right
    if (y < board.length - 1 && x < board[y + 1].length - 1) {
        arr.push({ x: x + 1, y: y + 1, type: board[y + 1][x + 1] });
    }

    // Bottom
    if (y < board.length - 1) {
        arr.push({ x: x, y: y + 1, type: board[y + 1][x] });
    }

    // Bottom Left
    if (y < board.length - 1 && x > 0) {
        arr.push({ x: x - 1, y: y + 1, type: board[y + 1][x - 1] });
    }

    // Left
    if (x > 0) {
        arr.push({ x: x - 1, y: y, type: board[y][x - 1] });
    }

    // Top Left
    if (x > 0 && y > 0) {
        arr.push({ x: x - 1, y: y - 1, type: board[y - 1][x - 1] });
    }

    return arr;
}

const render = board => {
    for (let y = 0; y < board.length; y++) {
        let s = "";
        for (let x = 0; x < board[y].length; x++) {
            s += board[y][x];
        }
        console.log(s);
    }
    console.log("");
}

const calculateResources = board => {
    let lumberyards = 0;
    let woods = 0;

    for (let y = 0; y < board.length; y++) {
        for (let x = 0; x < board[y].length; x++) {
            if (board[y][x] === "#") {
                lumberyards++;
            } else if (board[y][x] === "|") {
                woods++;
            }
        }
    }

    return lumberyards * woods;
}

const round = (initial, rounds) => {

    let history = [];

    let current = [];
    for (let y = 0; y < initial.length; y++) {
        current.push(initial[y].slice());
    }

    for (let i = 0; i < rounds; i++) {
        // render(current);
        const next = [];
        for (let y = 0; y < current.length; y++) {
            next.push([]);
            for (let x = 0; x < current[y].length; x++) {
                const tile = current[y][x];
                const neighbours = adjacent(current, x, y);

                if (tile === "." && neighbours.filter(c => c.type === "|").length >= 3) {
                    next[y].push("|");
                } else if (tile === "|" && neighbours.filter(c => c.type === "#").length >= 3) {
                    next[y].push("#");
                } else if (tile === "#") {
                    if (neighbours.some(c => c.type === "#") && neighbours.some(c => c.type === "|")) {
                        next[y].push("#");
                    } else {
                        next[y].push(".");
                    }
                } else {
                    next[y].push(tile);
                }
            }
        }

        const resources = calculateResources(current);
        const index1 = history.lastIndexOf(resources);

        if (index1 !== -1) {
            const index2 = history.lastIndexOf(resources, index1 - 1);

            if (history.length - index1 === index1 - index2) {
                return history.slice(index1)[(rounds - i) % (history.length - index1)];
            } else {
                history.push(resources);
            }
        } else {
            history.push(resources);
        }

        current = [];
        for (let y = 0; y < next.length; y++) {
            current.push(next[y].slice());
        }
    }

    // render(current);

    return calculateResources(current);
}

const resources1 = round(board, 10);
console.log("Part one: " + resources1);

const resources2 = round(board, 1000000000);
console.log("Part two: " + resources2);
