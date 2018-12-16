"use strict";

const part1 = (rounds) => {
    const recipes = [3, 7];
    let elf1 = 0, elf2 = 1;

    for (let i = 0; i < rounds + 10; i++) {
        var recipe = recipes[elf1] + recipes[elf2];
        var digits = Array.from(recipe.toString()).map(Number);

        recipes.push(...digits);

        elf1 = (elf1 + recipes[elf1] + 1) % recipes.length;
        elf2 = (elf2 + recipes[elf2] + 1) % recipes.length;
    }

    return recipes.slice(rounds, rounds + 10).join("");
}

const part2 = (input) => {
    let pattern = Array.from(input.toString()).map(Number)
    let buffer = circularBuffer(pattern.length + 1);
    let elf1 = 0, elf2 = 1;
    let recipes = [3, 7];

    buffer.push(3, 7);

    for (let i = 0; buffer.indexOf(...pattern) === -1; i++) {
        var recipe = recipes[elf1] + recipes[elf2];
        var digits = Array.from(recipe.toString()).map(Number);

        recipes.push(...digits);
        buffer.push(...digits);

        elf1 = (elf1 + recipes[elf1] + 1) % recipes.length;
        elf2 = (elf2 + recipes[elf2] + 1) % recipes.length;
    }

    return recipes.length - pattern.length - buffer.indexOf(...pattern);
}

const circularBuffer = (size) => {
    const buffer = Array(size).fill(0);
    let cursor = 0;

    return {
        count: () => cursor,
        indexOf: (...digits) => {
            let matches = 0;

            for (let i = 0; i < digits.length; i++) {
                if (buffer[(cursor + i) % buffer.length] === digits[i]) {
                    matches++;
                } else {
                    break;
                }
            }

            if (matches === digits.length) {
                return 1;
            }

            matches = 0;

            for (let i = 0; i < digits.length; i++) {
                if (buffer[(cursor + i + 1) % buffer.length] === digits[i]) {
                    matches++;
                } else {
                    break;
                }
            }

            if (matches === digits.length) {
                return 0;
            }

            return -1;
        },
        push: (...digits) => digits.forEach(digit => buffer[cursor++ % buffer.length] = digit)
    }
}

console.log("Part one: " + part1(157901))
console.log("Part two: " + part2(157901))