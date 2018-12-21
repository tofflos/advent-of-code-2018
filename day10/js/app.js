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

let points = fs.readFileSync("day10/js/10.in").toString().split("\r\n")
    .map(line => pattern.exec(line))
    .map(matches => {
        return new Point(Number(matches[1]), Number(matches[2]), Number(matches[3]), Number(matches[4]))
    });


const smallestSky = (stars, maximumWait) => {
    let smallestArea = Infinity;
    let smallestSecond = -1;

    for (let i = 0; i < maximumWait; i++) {
        const minX = Math.min(...stars.map(p => p.x));
        const maxX = Math.max(...stars.map(p => p.x));
        const minY = Math.min(...stars.map(p => p.y));
        const maxY = Math.max(...stars.map(p => p.y));

        const height = maxY - minY + 1;
        const width = maxX - minX + 1;
        const area = height * width;

        if (area < smallestArea) {
            smallestArea = area;
            smallestSecond = i;
        }

        stars.forEach(p => {
            p.x = p.x + p.dx;
            p.y = p.y + p.dy;
        });
    }

    return smallestSecond;
}

const renderSky = (stars, second) => {
    stars.forEach(p => {
        p.x = p.x + second * p.dx;
        p.y = p.y + second * p.dy;
    });

    const minX = Math.min(...stars.map(p => p.x));
    const maxX = Math.max(...stars.map(p => p.x));
    const minY = Math.min(...stars.map(p => p.y));
    const maxY = Math.max(...stars.map(p => p.y));

    const height = maxY - minY + 1;
    const width = maxX - minX + 1;

    const sky = Array(height).fill(0).map(() => Array(width).fill("."));

    const transpose = (p) => {
        return new Point(p.x - minX, p.y - minY, p.dx, p.dy);
    }

    stars.map(transpose).forEach(p => {
        sky[p.y][p.x] = "#";
    });

    for (let i = 0; i < sky.length; i++) {
        let row = "";
        for (let j = 0; j < sky[i].length; j++) {
            row += sky[i][j];
        }
        console.log(row);
    }
}

const probablyTheRightTime = smallestSky(points, 5 * 3600);

points = fs.readFileSync("day10/js/10.in").toString().split("\r\n")
    .map(line => pattern.exec(line))
    .map(matches => {
        return new Point(Number(matches[1]), Number(matches[2]), Number(matches[3]), Number(matches[4]))
    });

console.log("Part one:");
renderSky(points, probablyTheRightTime);
console.log("");
console.log("Part two: " + probablyTheRightTime);
