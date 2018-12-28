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

const round = initial => {
    let current = [];
    for (let y = 0; y < initial.length; y++) {
        current.push(initial[y].slice());
    }


    for (let i = 0; i < 10; i++) {
        render(current);
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

        current = [];
        for (let y = 0; y < next.length; y++) {
            current.push(next[y].slice());
        }
    }

    render(current);

    let woods = 0;
    for (let y = 0; y < current.length; y++) {
        for (let x = 0; x < current[y].length; x++) {
            if(current[y][x] === "|") {
                woods++;
            }
        }
    }

    let lumberyards = 0;
    for (let y = 0; y < current.length; y++) {
        for (let x = 0; x < current[y].length; x++) {
            if(current[y][x] === "#") {
                lumberyards++;
            }
        }
    }


    return woods * lumberyards;
}

const resourceValue = round(board);

console.log("Part one: " + resourceValue);
