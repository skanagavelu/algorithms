package queue;

import java.util.*;

public class TopPriority {

  public static void main(String[] args) {

    List<String> words = List.of("I", "LOVE", "CODE", "I", "LOVE", "ALGO");
    Map<String, Integer> frequencyMap = new HashMap<>();

    words.forEach(w -> frequencyMap.compute(w, (k, oldVal) -> oldVal == null ? 1 : oldVal + 1));

    //        PriorityQueue<String> p = new PriorityQueue<>((e1, e2) -> {
    //            if (frequencyMap.get(e1) == frequencyMap.get(e2)) {
    //                return String.CASE_INSENSITIVE_ORDER.compare(e1, e2);
    //            } else {
    //                return frequencyMap.get(e2) - frequencyMap.get(e1);
    //            }
    //        });

    //
    // natural order: (e1, e2) -> frequencyMap.get(e1).compareTo(frequencyMap.get(e2))
    // Creating Min Heap, using natural order (ascending order)
    PriorityQueue<String> p = new PriorityQueue<>(Comparator.comparing(frequencyMap::get));
    frequencyMap.forEach(
        (k, v) -> {
          p.add(k);
          if (p.size() > 2) {
            // Creates min heap, but removing the min value out of it, will only contains top values
            p.poll();
          }
        });

    words.forEach(
        e -> {
          p.add(e);
          if (p.size() > 2) {
            p.poll();
          }
        });
    p.forEach(System.out::println);
  }
}
