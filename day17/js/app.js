"use strict";

const fs = require("fs");
const lines = fs.readFileSync("day17/js/examples1.in").toString().split("\r\n");
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

// const ground = Array(maxY + 2).fill(0).map(() => Array(maxX + 3).fill('.'));
const ground = Array(maxY + 100).fill(0).map(() => Array(maxX + 3).fill('.'));

console.log(ground);


clay.forEach(p => {
    ground[p.y][p.x] = '#';
});

const fountains = [{ x: 500, y: 0 }];

fountains.forEach(p => {
    ground[p.y][p.x] = '+';
})

const waters = [];

const displayGround = (minX, maxX, minY, maxY) => {
    for (let y = minY; y < maxY; y++) {
        var s = "";
        for (let x = minX; x < maxX; x++) {
            s += ground[y][x] + " ";
        }

        console.log(s)
    }
};

for (let n = 0; n < 60; n++) {
    // Move
    waters.forEach(w => {
        if (ground[w.y + 1][w.x] !== '#' && !waters.some((water) => w.x === water.x && w.y + 1 === water.y)) {


            ground[w.y][w.x] = '|'
            w.y++;
            ground[w.y][w.x] = '|';
        } else if (w.d === "left") {
            if (ground[w.y][w.x - 1] !== '#' && !waters.some((water) => w.x - 1 === water.x && w.y === water.y)) {
                ground[w.y][w.x] = '|'
                w.x--;
                ground[w.y][w.x] = '|';
            } else {
                w.d = "right";
                if (ground[w.y][w.x + 1] === '#' || waters.some((water) => w.x + 1 === water.x && w.y === water.y)) {
                    ground[w.y][w.x] = '~';
                } else {
                    ground[w.y][w.x] = '|'
                    w.x++;
                    ground[w.y][w.x] = '|';
                }
            }
        } else if (w.d === "right") {
            if (ground[w.y][w.x + 1] !== '#' && !waters.some((water) => w.x + 1 === water.x && w.y === water.y)) {
                ground[w.y][w.x] = '|'
                w.x++;
                ground[w.y][w.x] = '|';
            } else {
                w.d = "left";
                if (ground[w.y][w.x - 1] === '#' || waters.some((water) => w.x - 1 === water.x && w.y === water.y)) {
                    ground[w.y][w.x] = '~';
                } else {
                    ground[w.y][w.x] = '|'
                    w.x--;
                    ground[w.y][w.x] = '|';
                }
            }
        }
    })

    // Create
    fountains.forEach(f => {
        if (ground[f.y + 1][f.x] !== '.' || !waters.includes([f.x, f.y + 1])) {
            ground[f.y + 1][f.x] = '|';
            waters.push({ x: f.x, y: f.y + 1, d: "left" });
        } else {
            console.log("Unable to create more water");
            process.exit();
        }
    })

    // let total = 0;

    
    // for (let i = 0; i < ground.length; i++) {
    //     for (let j = 0; j < ground[i].length; j++) {
    //         if (ground[i][j] === '|' || ground[i][j] === '~') {
    //             total++;
    //         }
    //     }
    // }


    // Display
    displayGround(494, 508, 0, 14);
    // console.log("Total: " + total + ", " + n);
    console.log("");
}





console.log(clay);
console.log(ground);


const family = [
    { name: "Elin", age: 39 },
    { name: "Erik", age: 40 },
    { name: "Ylva", age: 9 }
];

const map = fn => items => {
    let arr = [];

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        arr.push(fn(item));
    }

    return arr;
};

const getProperty = key => member => member[key];

const getAge = getProperty("age");
const getName = getProperty("name");

const getAges = map(getAge);
const getNames = map(getName);

const ages = getAges(family);
const names = getNames(family);

console.log(ages);
console.log(names);

// console.log(lines)
// console.log(lines.filter(line => line.startsWith("x")))
// console.log(ranges);

// class Clay {
//     constructor(x, y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// class Range {

// }

// class Water {

// }