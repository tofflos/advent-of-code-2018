const fs = require('fs');
const offsets = fs.readFileSync("1.in").toString().split("\n").map(Number);

// Part one

console.log("Part one: " + offsets.reduce((acc, cur) => acc + cur));

// Part two
function firstDuplicate(arr) {
    const history = new Set();

    let acc = 0;

    for (let idx = 0; ; idx++) {
        let cur = arr[idx % arr.length];
        acc = acc + cur;

        if (history.has(acc)) {
            return acc;
        }

        history.add(acc);
    }
}

// Testing solution against challenge set
console.log("Part two: " + firstDuplicate(offsets));