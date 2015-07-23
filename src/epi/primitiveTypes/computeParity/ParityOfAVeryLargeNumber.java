package epi.primitiveTypes.computeParity;

import java.util.Arrays;


/**
 * Problem 5.1 : How would you go about computing the parity of a very large number
 * of 64-bit nonnegative integers?
 * 
 * @author I311774
 *
 */
public class ParityOfAVeryLargeNumber {
public static void main(String[] args) {
	
    int mb = 1024*1024;
    
    //Getting the runtime reference from system
    Runtime runtime = Runtime.getRuntime();
     
    System.out.println("##### Heap utilization statistics [MB] #####");
     
    //Print used memory
    System.out.println("Used Memory:"
        + (runtime.totalMemory() - runtime.freeMemory()) / mb);

    //Print free memory
    System.out.println("Free Memory:"
        + runtime.freeMemory() / mb);
     
    //Print total available memory
    System.out.println("Total Memory:" + runtime.totalMemory() / mb);

    //Print Maximum available memory
    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    
    
//	int a[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][] = new int [2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][][][][][][][][][][][][][];
    
    
    
    
	int a[][][][][][][][][][][][][][][][] = new int [2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2];
    System.out.println("##### Heap utilization statistics [MB] #####");
    
    //Print used memory
    System.out.println("Used Memory:"
        + (runtime.totalMemory() - runtime.freeMemory()) / mb);

    //Print free memory
    System.out.println("Free Memory:"
        + runtime.freeMemory() / mb);
     
    //Print total available memory
    System.out.println("Total Memory:" + runtime.totalMemory() / mb);

    //Print Maximum available memory
    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    
	int b[][][][][][][][][][][][][][][][] = new int [2][2][2][2][2][2][2][2][2][2][2][2][2][2][2][2];
	//System.out.println(Arrays.deepToString(a));
    System.out.println("##### Heap utilization statistics [MB] #####");
    
    //Print used memory
    System.out.println("Used Memory:"
        + (runtime.totalMemory() - runtime.freeMemory()) / mb);

    //Print free memory
    System.out.println("Free Memory:"
        + runtime.freeMemory() / mb);
     
    //Print total available memory
    System.out.println("Total Memory:" + runtime.totalMemory() / mb);

    //Print Maximum available memory
    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    
    Object a1 = new Object();
    Object a2 = new Object();
    Object a3 = a1 ^ a2;
}
}
