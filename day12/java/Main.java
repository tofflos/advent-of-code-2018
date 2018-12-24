package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        var iterator = Files.readAllLines(Paths.get("12.in")).iterator();
        var initialState = iterator.next().substring("initial state: ".length());

        var notes = new HashMap<String, Character>();
        iterator.next();
        iterator.forEachRemaining(s -> notes.put(s.substring(0, 5), s.charAt(s.length() - 1)));

        System.out.println("Part one: " + part1(notes, initialState));
        System.out.println("Part two: " + part2(notes, initialState));
    }

    static long part1(HashMap<String, Character> notes, String initialState) {
        var bufferSize = 1024;
        var negativeBufferSize = 5;
        char currentPots[] = new char[bufferSize];

        Arrays.fill(currentPots, '.');
        System.arraycopy(initialState.toCharArray(), 0, currentPots, 0, initialState.length());

        for (long n = 0; n < 20; n++) {
            var nextPots = Arrays.copyOf(currentPots, bufferSize);

            for (int i = -negativeBufferSize; i < bufferSize - negativeBufferSize; i++) {
                var sb = new StringBuilder();
                for (int j = 0; j < 5; j++) {
                    sb.append(currentPots[Math.floorMod(i + j, bufferSize)]);
                }

                var current = sb.toString();

                nextPots[Math.floorMod(i + 2, bufferSize)] = notes.getOrDefault(current, '.');
            }

            System.arraycopy(nextPots, 0, currentPots, 0, bufferSize);
        }

        return calculateScore(currentPots, negativeBufferSize);
    }

    static long part2(HashMap<String, Character> notes, String initialState) {
        var bufferSize = 512;
        var negativeBufferSize = 5;
        var currentPots = new char[bufferSize];

        Arrays.fill(currentPots, '.');
        System.arraycopy(initialState.toCharArray(), 0, currentPots, 0, initialState.length());

        long generation = 0;
        Long previous = null;
        Long current = null;
        Long next = null;

        while (true) {
            var nextPots = Arrays.copyOf(currentPots, bufferSize);

            for (int i = -negativeBufferSize; i < bufferSize - negativeBufferSize; i++) {
                var sb = new StringBuilder();
                for (int j = 0; j < 5; j++) {
                    sb.append(currentPots[Math.floorMod(i + j, bufferSize)]);
                }

                var currentString = sb.toString();

                nextPots[Math.floorMod(i + 2, bufferSize)] = notes.getOrDefault(currentString, '.');
            }

            previous = current;
            current = calculateScore(currentPots, negativeBufferSize);
            next = calculateScore(nextPots, negativeBufferSize);

            if (previous != null && current - previous == next - current) {
                break;
            }

            System.arraycopy(nextPots, 0, currentPots, 0, bufferSize);

            generation++;
        }

        return previous + (50000000000L - (generation - 1L)) * (current - previous);
    }

    static long calculateScore(char[] arr, int negativeBufferSize) {
        long score = 0;

        for (int i = -negativeBufferSize; i < arr.length - negativeBufferSize; i++) {
            if (arr[Math.floorMod(i, arr.length)] == '#') {
                score += i;
            }
        }

        return score;
    }
}
