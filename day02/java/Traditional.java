package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Traditional {

    public static void main(String[] args) throws IOException {

        var boxes = Files.readAllLines(Paths.get("2.in"));

        // Part one
        var counters = new ArrayList<Map<String, Integer>>();
        for (var box : boxes) {
            counters.add(countLetters(box));
        }

        var twos = 0;
        var threes = 0;
        for (var map : counters) {
            if (map.values().contains(2)) {
                twos++;
            }
            if (map.values().contains(3)) {
                threes++;
            }
        }

        var checksum = twos * threes;

        System.out.println("Part one: " + checksum);

        // Part two
        String box1 = null;
        String box2 = null;

        outer:
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                if (isMatching(boxes.get(i), boxes.get(j))) {
                    box1 = boxes.get(i);
                    box2 = boxes.get(j);
                    break outer;
                }
            }
        }

        System.out.println("Part two: " + removeDifferences(box1, box2));
    }

    private static Map<String, Integer> countLetters(String letters) {
        var counters = new HashMap<String, Integer>();

        for (int i = 0; i < letters.length(); i++) {
            var letter = letters.substring(i, i + 1);
            var count = counters.getOrDefault(letter, 0);

            counters.put(letter, ++count);
        }

        return counters;
    }

    private static boolean isMatching(String box1, String box2) {
        var differences = 0;

        for (int i = 0; i < box1.length(); i++) {
            if (box1.charAt(i) != box2.charAt(i)) {
                differences++;
            }

            if (differences > 1) {
                return false;
            }

        }

        return true;
    }

    private static String removeDifferences(String box1, String box2) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < box1.length(); i++) {
            if (box1.charAt(i) == box2.charAt(i)) {
                sb.append(box1.charAt(i));
            }
        }
        
        return sb.toString();
    }
}