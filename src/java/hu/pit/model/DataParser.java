package hu.pit.model;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    public List<Integer> parse(List<String> lines) {
        return lines.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
