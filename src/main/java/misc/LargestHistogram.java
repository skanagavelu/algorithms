package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LargestHistogram {
	
	public static int histos[] = { 5, 6, 3, 6, 6, 4, 4 };

	public static void main(String[] args) {
		//Steps:
		/*
		 * 1. Add the number if it is not available (SEARCH)
		 * 2. Double the value which are less than or equal to the next int (UPDATE)
		 * 3. Remove the number when it is lower than the next int.
		 * 4. Maintain the max area found for a number
		 */
		Map<Integer, Integer> histoSequences = new ConcurrentHashMap<>();
		MaxHisto maxHistoSequence = new MaxHisto();

		for (int i = 0; i < histos.length; i++) {
			int histo = histos[i];
			
			if(!histoSequences.containsKey(histos[i])) {
				histoSequences.put(histos[i], 0);
			}
			
			for(Integer histoSeq : histoSequences.keySet()) {
				if(histoSeq > histo) {
					histoSequences.remove(histoSeq);
                    histoSeq = histo;
				} 
				int newHistoSeqValue = (histoSequences.get(histoSeq) + histoSeq);
				histoSequences.put(histoSeq, newHistoSeqValue);
				if(newHistoSeqValue > maxHistoSequence.value) {
					maxHistoSequence.key = histoSeq;
					maxHistoSequence.value = newHistoSeqValue;
				}
				
			}
			

		}
		System.out.println(" Max Histo key: " + maxHistoSequence.key + "  value: " + maxHistoSequence.value);
	}
	
	static class MaxHisto {
		int key;
		int value;
	}
}
