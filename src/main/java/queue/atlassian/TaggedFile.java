package queue.atlassian;

public record TaggedFile(String tag, Long size) implements Comparable<TaggedFile> {

  public TaggedFile {
    if (tag == null || tag.isEmpty()) {
      tag = "default";
    }
  }

  @Override
  public int compareTo(TaggedFile o) {
    return 0;
  }
}
