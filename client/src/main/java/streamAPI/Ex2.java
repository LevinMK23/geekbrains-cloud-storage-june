package streamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {
    public static void main(String[] args) throws Throwable {
//        Stream.of("123", "fff", "1f3", "123", "123")
//                .map(s -> s.replaceAll("[a-z]+", "0"))
//                .filter(s -> s.matches("[0-9]+"))
//                .distinct()
//                .sorted()
//                .forEach(System.out::println);
        String result = Files.lines(Paths.get("./", "file.txt"))
                .filter(arg -> !arg.isEmpty())
                .flatMap(arg -> Stream.of(arg.split(" +")))
                .filter(arg -> !arg.isEmpty())
                .sorted()
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println(result);
        System.out.println(Files
                .lines(Paths.get("./", "file.txt"))
                .filter(s -> s.length() > 1000)
                .findAny().orElseThrow(() -> new RuntimeException("LOL")));
    }
}
