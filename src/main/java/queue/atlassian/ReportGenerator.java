package queue.atlassian;

import java.util.*;
import java.util.stream.Collectors;

/**
 * file2.txt (size: 200) in collection "collection1" file3.txt (size: 200) in collection
 * "collection1" file4.txt (size: 300) in collection "collection2" file5.txt (size: 10) file6.txt
 * (size: 20)
 */
public class ReportGenerator {

  public Report generateReports(List<TaggedFile> taggedFiles, int topNumber) {
    return new Report(sumFileSize(taggedFiles), topCollectionBySize(taggedFiles, topNumber));
  }

  public long sumFileSize(List<TaggedFile> taggedFiles) {

    return taggedFiles.stream().map(TaggedFile::size).reduce(0L, Long::sum);
    //    return taggedFiles.stream()
    //        .filter(Predicate.not(Objects::isNull))
    //        .mapToLong(TaggedFile::getSize)
    //        .sum();
  }

  public PriorityQueue<TaggedFile> topCollectionBySize(
      List<TaggedFile> taggedFiles, int topNumber) {

    var queue = new PriorityQueue<>(topNumber, new TopSizeComparator());
    for (var file : taggedFiles) {
      if (file == null) {
        continue;
      }
      queue.add(file);
      if (queue.size() > topNumber) {
        queue.poll();
      }
    }
    return queue;
  }

  public Map<String, Long> groupByTag(List<TaggedFile> taggedFiles) {

    return taggedFiles.stream()
        .collect(Collectors.groupingBy(TaggedFile::tag, Collectors.summingLong(TaggedFile::size)));

    //    Map<String, Long> map = new HashMap<>();
    //    for (var file : taggedFiles) {
    //      map.compute(
    //          file.getTag(), (k, v) -> (v == null) ? file.getSize() : Long.sum(v,
    // file.getSize()));
    //    }
    //    return map;
  }
}
