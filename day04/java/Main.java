package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        var biconsumer = new BiConsumer<List<Range>, String>() {
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

        var collector = Collector.of(ArrayList::new, biconsumer, (left, right) -> {
            left.addAll(right);
            return left;
        });

        var ranges = Files.readAllLines(Paths.get("4.in")).stream().sorted().collect(collector);

        var guards_accumulated_sleep = ranges.stream().collect(Collectors.toMap(r -> r.guard, r -> r.stop - r.start, Integer::sum));
        var sleepiest_guard = guards_accumulated_sleep.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        var sleepiest_minute = ranges.stream().filter(r -> r.guard == sleepiest_guard)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Main::sleepiest_minute));

        System.out.println("Part one: " + sleepiest_guard * sleepiest_minute);

        var sleepiest_minutes = ranges.stream().collect(Collectors.groupingBy(r -> r.guard,
                Collectors.collectingAndThen(Collectors.toList(), Main::sleepiest_minute)));

        var highest_sleep_occurrences = ranges.stream().collect(Collectors.groupingBy(r -> r.guard,
                Collectors.collectingAndThen(Collectors.toList(), Main::highest_sleep_count)));

        var most_predictable_guard = highest_sleep_occurrences.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        var most_predictable_minute = sleepiest_minutes.get(most_predictable_guard);

        System.out.println("Part two: " + most_predictable_guard * most_predictable_minute);
    }

    static int highest_sleep_count(List<Range> ranges) {
        var minutes = IntStream.range(0, 60)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> ranges.stream().filter(r -> r.start <= i && i < r.stop).count()));
     
        return minutes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue().intValue();
    }

    static int sleepiest_minute(List<Range> ranges) {
        Map<Integer, Long> minutes = IntStream.range(0, 60)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> ranges.stream().filter(r -> r.start <= i && i < r.stop).count()));

        return minutes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
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
