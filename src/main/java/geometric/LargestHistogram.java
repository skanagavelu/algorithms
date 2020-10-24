package geometric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LargestHistogram {
	
	public static int histograms[] = { 5, 6, 3, 6, 6, 4, 4 };

	public static void main(String[] args) {
		//Steps:
		/*
		 * 1. Add the number if it is not available (SEARCH)
		 * 2. Double the value which are less than or equal to the next int (UPDATE)
		 * 3. Remove the number when it is lower than the next int.
		 * 4. Maintain the max area found for a number
		 */
		Map<Integer, Integer> histogramSequences = new ConcurrentHashMap<>();
		MaxHistogram maxHistogramSequence = new MaxHistogram();

		for (int i = 0; i < histograms.length; i++) {
			int histogram = histograms[i];
			
			if(!histogramSequences.containsKey(histograms[i])) {
				histogramSequences.put(histograms[i], 0);
			}
			
			for(Integer histogramSequence : histogramSequences.keySet()) {
				if(histogramSequence > histogram) {
					histogramSequences.remove(histogramSequence);
                    histogramSequence = histogram;
				} 
				int newHistogramSeqValue = (histogramSequences.get(histogramSequence) + histogramSequence);
				histogramSequences.put(histogramSequence, newHistogramSeqValue);
				if(newHistogramSeqValue > maxHistogramSequence.value) {
					maxHistogramSequence.key = histogramSequence;
					maxHistogramSequence.value = newHistogramSeqValue;
				}
				
			}
		}
		System.out.println(
				" Max Histogram key: " + maxHistogramSequence.key +
				"  value: " + maxHistogramSequence.value);
	}
	
	static class MaxHistogram {
		int key;
		int value;
	}
}
