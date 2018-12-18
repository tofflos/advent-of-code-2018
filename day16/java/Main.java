package com.example;

import java.util.Arrays;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        int[] registers = {0, 0, 0, 0};

        TriConsumer<Integer, Integer, Integer> addr = (a, b, c) -> registers[c] = registers[a] + registers[b];
        TriConsumer<Integer, Integer, Integer> addi = (a, b, c) -> registers[c] = registers[a] + b;
        TriConsumer<Integer, Integer, Integer> mulr = (a, b, c) -> registers[c] = registers[a] * registers[b];
        TriConsumer<Integer, Integer, Integer> muli = (a, b, c) -> registers[c] = registers[a] * b;
        TriConsumer<Integer, Integer, Integer> banr = (a, b, c) -> registers[c] = registers[a] & registers[b];
        TriConsumer<Integer, Integer, Integer> bani = (a, b, c) -> registers[c] = registers[a] & b;
        TriConsumer<Integer, Integer, Integer> borr = (a, b, c) -> registers[c] = registers[a] | registers[b];
        TriConsumer<Integer, Integer, Integer> bori = (a, b, c) -> registers[c] = registers[a] | b;
        TriConsumer<Integer, Integer, Integer> setr = (a, b, c) -> registers[c] = registers[a];
        TriConsumer<Integer, Integer, Integer> seti = (a, b, c) -> registers[c] = a;
        TriConsumer<Integer, Integer, Integer> gtir = (a, b, c) -> registers[c] = a > registers[b] ? 1 : 0;
        TriConsumer<Integer, Integer, Integer> gtri = (a, b, c) -> registers[c] = registers[a] > b ? 1 : 0;
        TriConsumer<Integer, Integer, Integer> gtrr = (a, b, c) -> registers[c] = registers[a] > registers[b] ? 1 : 0;
        TriConsumer<Integer, Integer, Integer> eqir = (a, b, c) -> registers[c] = a == registers[b] ? 1 : 0;
        TriConsumer<Integer, Integer, Integer> eqri = (a, b, c) -> registers[c] = registers[a] == b ? 1 : 0;
        TriConsumer<Integer, Integer, Integer> eqrr = (a, b, c) -> registers[c] = registers[a] == registers[b] ? 1 : 0;

        var instructions = Set.of(addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr);

        int[] before = {3, 2, 1, 1};
        int[] after = {3, 2, 2, 1};

        var count = instructions.stream().filter(i -> {
            System.arraycopy(before, 0, registers, 0, 4);
            i.accept(2, 1, 2);
            return Arrays.equals(after, registers);
        }).count();

        System.out.println(count);
    }
}

@FunctionalInterface
interface TriConsumer<T, U, V> {

    void accept(T t, U u, V v);
}
