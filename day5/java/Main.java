package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        var polymers = Files.readString(Paths.get("5.in"));

        System.out.println("Part one: " + react(polymers).length());

        var reacted_polymers = new HashMap<Character, Integer>();

        for (var unit = 'a'; unit <= 'z'; unit++) {
            String t = react(polymers.replace(String.valueOf(unit), "").replace(String.valueOf(toggleCase(unit)), ""));
            reacted_polymers.put(unit, t.length());
        }

        System.out.println("Part two: " + reacted_polymers.values().stream().min(Integer::compare).get());
    }

    static String react(String s) {
        var sb = new StringBuilder(s);
        var i = 0;

        for (; i < sb.length() - 1;) {
            char c1 = sb.charAt(i);
            char c2 = sb.charAt(i + 1);

            if (c1 == toggleCase(c2)) {
                sb.delete(i, i + 2);
                i = 0;
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    static char toggleCase(char c) {
        return Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
    }
}