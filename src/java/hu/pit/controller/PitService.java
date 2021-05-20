package hu.pit.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PitService {

    public static final String SEPARATOR = "_";

    private final List<Integer> depths;

    public PitService(List<Integer> depths) {
        this.depths = depths;
    }

    /**
     * 1. feladat
     */
    public int getDepthCount() {
        return depths.size();
    }

    /**
     * 2. feladat
     */
    public int getDepthAtCertainDistance(int distance) {
        return depths.get(distance);
    }

    /**
     * 3. feladat
     */
    public String getUntouchedArea() {
        double percent = getUntouchedSurfaceCount() * 100.0 / getDepthCount();
        return String.format("%5.2f%%", percent);
    }

    private long getUntouchedSurfaceCount() {
        return depths.stream()
                .filter(i -> i == 0)
                .count();
    }

    /**
     * 4-5. feladat
     */

    public List<String> getPits() {
        String[] pits = getDepthsAsString().split(SEPARATOR);
        return Arrays.stream(pits)
                .filter(i -> i.length() > 0)
                .collect(Collectors.toList());
    }

    private String getDepthsAsString() {
        return depths.stream()
            .map(this::transform)
            .collect(Collectors.joining());
    }

    private String transform(int value) {
        return value == 0 ? SEPARATOR : String.valueOf(value);
    }

    /**
     * 6. feladat
     */
    public String getPitDetails(int distance) {
        return getDepthAtCertainDistance(distance) == 0
                ? "Az adott helyen nincs gödör."
                : printAnswers(distance);
    }
    private String printAnswers(int distance) {
        StringBuilder sb = new StringBuilder();
        List<Integer> actualPit = getActualPit(distance, sb);
        int pitMaxDepth = getPitMaxDepth(actualPit);
        int volume = getPitVolume(actualPit);
        sb.append("b)\n")
                .append(isMonotonous(pitMaxDepth, actualPit)
                        ? "Folyamatosan mélyül.\n"
                        : "Nem mélyül folyamatosan.\n");
        sb.append("c)\n").append("A legnagyobb mélysége ")
                .append(pitMaxDepth).append(" méter.\n");
        sb.append("d)\n").append("A térfogata ")
                .append(volume * 10).append(" m^3.\n");
        sb.append("e)\n").append("A vízmennyiség ")
                .append((volume - actualPit.size()) * 10).append(" m^3.");
        return sb.toString();
    }

    /**
     * 6.a
     */
    private List<Integer> getActualPit(int distance, StringBuilder sb) {
        int begin = distance - 1;
        while (depths.get(begin) > 0) begin--;
        int end = distance + 1;
        while (depths.get(end) > 0) end++;
        sb.append("a)\n")
                .append("A gödör kezdete: ")
                .append(begin + 2)
                .append(" méter, a gödör vége: ")
                .append(end)
                .append(" méter.\n");
        return depths.subList(begin + 1, end);
    }

    /**
     * 6.b
     */
    private boolean isMonotonous(int maxDepth, List<Integer> pit) {
        int pos = pit.indexOf(maxDepth);
        boolean monotonous = true;
        for (int i = 0; i < pos; i++) {
            if (pit.get(i) > pit.get(i + 1)) {
                monotonous = false;
            }
        }
        for (int i = pos; i < pit.size() - 1; i++) {
            if (pit.get(i) < pit.get(i + 1)) {
                monotonous = false;
            }
        }
        return monotonous;
    }

    /**
     * 6.c
     */
    private int getPitMaxDepth(List<Integer> pit) {
        return pit.stream()
                .max(Comparator.naturalOrder())
                .get();
    }

    /**
     * 6.d
     */
    private int getPitVolume(List<Integer> pit) {
        return pit.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
