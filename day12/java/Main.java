package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        var iterator = Files.readAllLines(Paths.get("12.in")).iterator();
        var initialState = iterator.next().substring("initial state: ".length());
        var notes = new HashMap<String, Character>();

        iterator.next();
        iterator.forEachRemaining(s -> notes.put(s.substring(0, 5), s.charAt(s.length() - 1)));

        System.out.println("Part one: " + part1(notes, initialState));
    }

    static int part1(HashMap<String, Character> notes, String initialState) {
        var bufferSize = 1024;
        char currentPots[] = new char[bufferSize];

        Arrays.fill(currentPots, '.');
        System.arraycopy(initialState.toCharArray(), 0, currentPots, 0, initialState.length());

        for (long n = 0; n < 20; n++) {
            var nextPots = Arrays.copyOf(currentPots, bufferSize);

            for (int i = -5; i < currentPots.length - 5; i++) {
                var sb = new StringBuilder();
                for (int j = 0; j < 5; j++) {
                    sb.append(currentPots[Math.floorMod(i + j, bufferSize)]);
                }

                var current = sb.toString();

                nextPots[Math.floorMod(i + 2, bufferSize)] = notes.getOrDefault(current, '.');
            }

            System.arraycopy(nextPots, 0, currentPots, 0, bufferSize);
        }

        var count = 0;

        for (int i = -5; i < currentPots.length - 5; i++) {
            if (currentPots[Math.floorMod(i, currentPots.length)] == '#') {
                count += i;
            }
        }

        return count;
    }
}
