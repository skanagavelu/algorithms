package queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopPriority {

    public static void main(String[] args) {

        List<String> words = List.of("I", "LOVE", "CODE", "I", "LOVE", "ALGO");
        Map<String, Integer> frequencyMap = new HashMap<>();
        words.forEach(w -> frequencyMap.compute(w, (k, oldVal) -> oldVal == null ? 1 : oldVal + 1));

        PriorityQueue<String> p = new PriorityQueue<>((e1, e2) -> {
            if (frequencyMap.get(e1) == frequencyMap.get(e2)) {
                return String.CASE_INSENSITIVE_ORDER.compare(e1, e2);
            } else {
                return frequencyMap.get(e2) - frequencyMap.get(e1);
            }
        });
        words.forEach(e -> {
            p.add(e);
            if (p.size() > 2) { p.poll(); }
        });
        p.forEach(System.out::println);
    }
}
