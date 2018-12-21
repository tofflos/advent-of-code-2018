"use strict";

const fs = require("fs");
const lines = fs.readFileSync("day17/js/17.in").toString().split("\r\n");
const patternX = /x=(\d+), y=(\d+)..(\d+)/;
const patternY = /y=(\d+), x=(\d+)..(\d+)/;

const rangeInclusive = (from, to) => {
    let arr = [];
    for (let i = from; i <= to; i++) {
        arr.push(i);
    }
    return arr;
};

const rangesX = lines.filter(line => line.startsWith("x"))
    .map(line => patternX.exec(line))
    .map(match => [Number(match[1]), rangeInclusive(Number(match[2]), Number(match[3]))])
    .map(([x, ys]) => {
        let arr = [];
        for (let i = 0; i < ys.length; i++) {
            arr.push({ x: x, y: ys[i] });
        }
        return arr;
    })
    .flat();

const rangesY = lines.filter(line => line.startsWith("y"))
    .map(line => patternY.exec(line))
    .map(match => [Number(match[1]), rangeInclusive(Number(match[2]), Number(match[3]))])
    .map(([y, xs]) => {
        let arr = [];
        for (let i = 0; i < xs.length; i++) {
            arr.push({ x: xs[i], y: y });
        }
        return arr;
    })
    .flat();

const clay = rangesX.concat(rangesY);

const minX = Math.min(...clay.map(c => c.x))
const maxX = Math.max(...clay.map(c => c.x))
const minY = Math.min(...clay.map(c => c.y))
const maxY = Math.max(...clay.map(c => c.y))

const ground = Array(maxY + 2).fill(0).map(() => Array(maxX + 3).fill('.'));

clay.forEach(p => {
    ground[p.y][p.x] = '#';
});

const fountains = [{ x: 500, y: minY - 1 }];

fountains.forEach(p => {
    ground[p.y][p.x] = '+';
})

const flow = (x, y, parentY) => {
    ground[y][x] = '|';

    if (y >= maxY) {
        return;
    }

    if (ground[y + 1][x] === '.') {
        flow(x, y + 1, y);
    }

    if ((ground[y + 1][x] === '#' || ground[y + 1][x] === '~') && ground[y][x - 1] === '.') {
        flow(x - 1, y, y);
    }

    if ((ground[y + 1][x] === '#' || ground[y + 1][x] === '~') && ground[y][x + 1] === '.') {
        flow(x + 1, y, y);
    }

    if (y !== parentY) {
        let arr = [];

        let cursor1 = x;
        while (ground[y][cursor1] === '|') {
            arr.push({ x: cursor1--, y: y });
        }

        let cursor2 = x;
        while (ground[y][cursor2] === '|') {
            arr.push({ x: cursor2++, y: y });
        }


        if (arr.every(p => ground[p.y + 1][p.x] === '#' || ground[p.y + 1][p.x] === '~')) {
            arr.forEach(p => ground[p.y][p.x] = '~');
        }

    }
}

flow(500, minY);

let total = 0;

for (let i = 0; i < ground.length; i++) {
    for (let j = 0; j < ground[i].length; j++) {
        if (ground[i][j] === '|' || ground[i][j] === '~') {
            total++;
        }
    }
}

console.log("Part one: " + total);

let total2 = 0;

for (let i = 0; i < ground.length; i++) {
    for (let j = 0; j < ground[i].length; j++) {
        if (ground[i][j] === '~') {
            total2++;
        }
    }
}

console.log("Part two: " + total2);
