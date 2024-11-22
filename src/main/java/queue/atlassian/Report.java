package queue.atlassian;

import java.util.PriorityQueue;

public record Report(long size, PriorityQueue<TaggedFile> topNCollection) {}
