package queue.atlassian;

import java.util.Comparator;

public class TopSizeComparator implements Comparator<TaggedFile> {

    /**
     * @return int :if the size is same then name natural order will be returned
     */
    public int compare(TaggedFile f1, TaggedFile f2) {

        int sizeComparisonResult = f2.size().compareTo(f1.size());
        return sizeComparisonResult == 0 ?  f1.tag().compareTo(f2.tag()) : sizeComparisonResult;
        }
    }

