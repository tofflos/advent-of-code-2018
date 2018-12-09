package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Paths.get("1.in"));
        var offsets = new ArrayList<Integer>();

        for(var line : lines) {
            offsets.add(Integer.parseInt(line));
        }

        // Part one
        System.out.println("Part one: " + part1(offsets));

        // Part two
        System.out.println("Part two: " + part2(offsets));
    }

    private static Integer part1(List<Integer> integers) {
        var total = 0;

        for(var integer : integers) {
            total += integer;
        }

        return total;
    }

    private static Integer part2(List<Integer> integers) {
        var history = new HashSet<Integer>();
        var acc = 0;

        for (var idx = 0;; idx++) {
            acc = acc + integers.get(idx % integers.size());

            if (history.contains(acc)) {
                return acc;
            }

            history.add(acc);
        }
    }
}