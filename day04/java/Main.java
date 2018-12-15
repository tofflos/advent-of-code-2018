package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        var parser = new BiConsumer<List<Range>, String>() {
            Pattern begins = Pattern.compile(".*#(\\d+) begins shift");

            int guard, start, stop;

            @Override
            public void accept(List<Range> ranges, String s) {
                if (s.contains("begins")) {
                    var matcher = begins.matcher(s);
                    matcher.find();
                    guard = Integer.parseInt(matcher.group(1));
                } else if (s.contains("asleep")) {
                    start = Integer.parseInt(s.substring(15, 17));
                } else {
                    stop = Integer.parseInt(s.substring(15, 17));
                    ranges.add(new Range(guard, start, stop));
                }
            }
        };

        Collector<String, ?, List<Range>> collector = Collector.of(ArrayList::new,
                parser,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                });

        var ranges = Files.readAllLines(Paths.get("4.in")).stream().sorted().collect(collector);

        var guards_ranges = ranges.stream().collect(Collectors.groupingBy(r -> r.guard));

        var guards_aggregatedSleep = ranges.stream().collect(
                Collectors.groupingBy(r -> r.guard,
                        Collectors.mapping(r -> r.stop - r.start,
                                Collectors.reducing(Integer::sum))));

        var guard = guards_aggregatedSleep.entrySet().stream()
                .max((a, b) -> Integer.compare(a.getValue().get(), b.getValue().get())).get().getKey();

        var guards_minutes = guards_ranges.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), e -> occurrences(e.getValue())));

        var sleep_max_occurrences = guards_minutes.get(guard).stream().max(Integer::compare).get();
        var sleep_max_minute = guards_minutes.get(guard).indexOf(sleep_max_occurrences);

        System.out.println("Part one: " + guard * sleep_max_minute);
    }

    static List<Integer> occurrences(List<Range> ranges) {
        List<Integer> minutes = IntStream.of(new int[60]).boxed().collect(Collectors.toList());

        IntStream.range(0, 60).forEach(minute -> {
            ranges.stream()
                    .filter(r -> r.start <= minute && minute < r.stop)
                    .forEach(r -> minutes.set(minute, minutes.get(minute) + 1));
        });

        return minutes;
    }
}

class Range {

    int guard, start, stop;

    public Range(int guard, int start, int stop) {
        this.guard = guard;
        this.start = start;
        this.stop = stop;
    }
}
