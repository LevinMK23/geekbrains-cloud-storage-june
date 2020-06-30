package streamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex3 {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("./", "file.txt"))
                .flatMap(arg -> Stream.of(arg.split(" +")))
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^a-z]+", ""))
                .filter(arg -> !arg.isEmpty())
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream().sorted(Map.Entry.comparingByValue((a, b) -> -a + b))
                .forEach(arg -> System.out.println(arg.getKey() + " : " + arg.getValue()));
        //.reduce(0, Integer::sum);
        //System.out.println(map);
    }
}
