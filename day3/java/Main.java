package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        var claims = Files.readAllLines(Paths.get("3.in")).stream()
                .map(Claim::parse)
                .collect(Collectors.toList());

        var crowded_squares = 0;

        for (var x = 0; x < 1000; x++) {
            for (var y = 0; y < 1000; y++) {
                var square = new Claim(-1, x, y, 1, 1);
                var count = 0;

                for (var claim : claims) {
                    if (overlaps(square, claim)) {
                        count++;
                    }
                }

                if (count >= 2) {
                    crowded_squares++;
                }
            }
        }

        System.out.println("Part one: " + crowded_squares);

        Claim intact_claim = null;

        for (var c1 : claims) {
            var count = 0;

            for (var c2 : claims) {
                if (overlaps(c1, c2) && c1.id != c2.id) {
                    count++;
                }
            }
            
            if(count == 0) {
                intact_claim = c1;
                break;
            }
        }

        System.out.println("Part two: " + intact_claim.id);
    }

    static boolean overlaps(Claim c1, Claim c2) {
        return !((c1.x > (c2.x + c2.w - 1))
                || (c2.x > (c1.x + c1.w - 1))
                || (c1.y > (c2.y + c2.h - 1))
                || (c2.y > (c1.y + c1.h - 1)));
    }

    static class Claim {

        private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        final int id, x, y, w, h;

        private Claim(int id, int x, int y, int w, int h) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        static Claim parse(String s) {
            var matcher = PATTERN.matcher(s);
            matcher.find();

            return new Claim(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)));
        }

        @Override
        public String toString() {
            return "Claim{" + "id=" + id + ", x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + '}';
        }
    }
}