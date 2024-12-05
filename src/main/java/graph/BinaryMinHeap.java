package graph;

import java.util.ArrayList;
import java.util.List;

public class BinaryMinHeap<T extends Comparable<T>> {

    List<T> nodes = new ArrayList<>();
//    LinkedList

    public void add(int weight, T node) {
        nodes.add(node);
        insertionHeapify(nodes.size() - 1);
    }

    
    private void insertionHeapify(int index){

        int parentIndex = (index - 1) / 2;
        if (parentIndex > -1) {

             int leftIndex = 2 * parentIndex + 1;
             int rightIndex = 2 * parentIndex + 2;

             if (leftIndex < nodes.size()) {
                 if (rightIndex < nodes.size()) {

                     if (nodes.get(parentIndex).compareTo(nodes.get(leftIndex)) <= 0 &&
                         nodes.get(parentIndex).compareTo(nodes.get(rightIndex)) <= 0) {
                         return;
                     }

                     if (nodes.get(rightIndex).compareTo(nodes.get(leftIndex)) < 0) {

                         swap(rightIndex, parentIndex);
                         insertionHeapify(parentIndex);
                     } else {

                         swap(leftIndex, parentIndex);
                         insertionHeapify(parentIndex);
                     }
                 } else {
                     if (nodes.get(leftIndex).compareTo(nodes.get(parentIndex)) < 0) {
                         swap(leftIndex, parentIndex);
                         insertionHeapify(parentIndex);
                     }
                 }
             }
        }
    }


    public T extractMin() {

        swap(0, nodes.size() -1);
        T minNode = nodes.remove(nodes.size() -1);
        deletionHeapify(0);
        return minNode;
    }

    private void deletionHeapify(int index) {

        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < nodes.size()) {
            if (rightIndex < nodes.size()) {

                if (nodes.get(index).compareTo(nodes.get(leftIndex)) <= 0 &&
                    nodes.get(index).compareTo(nodes.get(rightIndex)) <= 0) {
                    return;
                }

                if (nodes.get(rightIndex).compareTo(nodes.get(leftIndex)) < 0) {

                    swap(rightIndex, index);
                    insertionHeapify(rightIndex);
                } else {

                    swap(leftIndex, index);
                    insertionHeapify(leftIndex);
                }
            } else {
                if (nodes.get(leftIndex).compareTo(nodes.get(index)) < 0) {
                    swap(leftIndex, index);
                    insertionHeapify(leftIndex);
                }
            }
        }
    }

    public void swap(int from, int to) {

        T newToNode = nodes.get(from);
        nodes.set(from, nodes.get(to));
        nodes.set(to, newToNode);
    }

 
    public boolean containsData(T key){return false;}

    /**
     * Get the heap min without extracting the key
     */
    public T min(){return  null;}

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return false;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T data, int newWeight) {

    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key){return 0;};

    /**
     * Returns the min node of the heap
     */
//    public Node extractMinNode(){return null;};

    private void printPositionMap() {}

    private void updatePositionMap(T data1, T data2, int pos1, int pos2) {}

    public void printHeap() {}

    public static void main(String args[]){
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "abc");
        heap.add(4, "abcd");
        heap.add(8, "abcdefgh");
        heap.add(10, "abcdefghij");
        heap.add(5, "abcde");
        heap.add(6, "abcdef");
        heap.add(2,"ab");
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());

        //        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}
