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

console.log("Part one: " + part1(157901));