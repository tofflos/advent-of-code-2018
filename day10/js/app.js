"use strict";

const fs = require("fs");
const pattern = /position=<(.+),(.+)> velocity=<(.+),(.+)>/;
class Point {
    constructor(x, y, dx, dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
}

const points = fs.readFileSync("day10/js/examples1.in").toString().split("\r\n")
    .map(line => pattern.exec(line))
    .map(matches => {
        return new Point(Number(matches[1]), Number(matches[2]), Number(matches[3]), Number(matches[4]))
    });

for (let n = 0; n < 5; n++) {
    console.log(`${n} seconds: `)

    const minX = Math.min(...points.map(p => p.x));
    const maxX = Math.max(...points.map(p => p.x));
    const minY = Math.min(...points.map(p => p.y));
    const maxY = Math.max(...points.map(p => p.y));

    const height = maxY - minY + 1;
    const width = maxX - minX + 1;
    const sky = Array(height).fill(0).map(() => Array(width).fill("."));

    const transpose = (p) => {
        return new Point(p.x - minX, p.y - minY, p.dx, p.dy);
    }

    points.map(transpose).forEach(p => {
        sky[p.y][p.x] = "#";
    });

    for (let i = 0; i < sky.length; i++) {
        let row = "";
        for (let j = 0; j < sky[i].length; j++) {
            row += sky[i][j];
        }
        console.log(row);
    }
    console.log("");

    points.forEach(p => {
        p.x = p.x + p.dx;
        p.y = p.y + p.dy;
    });
}
