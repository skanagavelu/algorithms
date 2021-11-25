package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// Print the intervals that are free from for schedule
public class MeetingSchedules {

    public static void main(String[] args) {
        Range r1 = new Range(0.30, 1.30);
        Range r2 = new Range(9.30, 10.30);
        Range r3 = new Range(8.30, 11.30);
        Range r4 = new Range(12.30, 15.30);
        Range r5 = new Range(16.30, 17.30);
        List<Range> ranges = new ArrayList<>(Arrays.asList(r1, r2, r4, r5));
        Iterator<Range> iterator = ranges.iterator();
        List<Range> overLapped = new ArrayList<>();

        while (iterator.hasNext()) {
            Range other = iterator.next();
            if(other.isOverlaping(r3)) {
                overLapped.add(other);
                iterator.remove();
            }
        }
        overLapped.stream().forEach(overLappingRange -> r3.merge(overLappingRange));
        ranges.add(r3);
        System.out.println(ranges);
    }
}


class Range implements Comparable<Range>{

    double start;
    double end;

    Range(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public boolean isOverlaping(Range other) {

        return this.start > other.start || this.end > other.end;
    }

    public boolean isBefore(Range other) {

        return this.start < other.start || this.end < other.end;
    }

    public Range merge(Range other) {

        if (this.start > other.start) {
            this.start = other.start;
        }
        if (this.end < other.end) {
            this.end = other.end;
        }
        return this;
    }

    public static Range interval(Range first, Range second) {

        return first.isBefore(second) ? new Range(first.end, second.start) : new Range(second.end, first.start);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Range range = (Range) o;
        return Double.compare(range.start, start) == 0 && Double.compare(range.end, end) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(Range other) {

       return this.start < other.start ? -1 : this.start > other.start ? 1 : this.end < other.end ? -1 :
                                                                         this.end > other.end ? 1 : 0;
    }

    @Override
    public String toString() {

        return "Range{" +
               "start=" + start +
               ", end=" + end +
               '}';
    }
}