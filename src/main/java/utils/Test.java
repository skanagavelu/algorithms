package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static Map<Integer, Integer> addressToValue = Map.of(10, 10, 20, 20, 30, 30);

    public static void main(String[] args) {

        Deque<String> l = new LinkedList<>();
        l.offerLast("1");
        l.offerLast("2");
        l.offerLast("3");

        l.pollFirst();
        System.out.println(l);

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(5, Integer::sum);



        List<Integer> listOfNumbers1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24
                ,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50);
        listOfNumbers1.parallelStream().forEach(number ->
                                                       System.out.println(number + " " + Thread.currentThread().getName())
                                              );



//        Arrays.stream("hello".chars())
        Character c = (Character) 'h';
        "hello".toCharArray().toString();
        "hello".chars().mapToObj(o -> (Character)(char)o).toArray();
        Stream.of("a", "b", "c").map(String::toUpperCase).forEach(System.out::println);
        Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.joining(", ", "prefix", "suffix"));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Integer::compareTo);
        PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>(Comparator.reverseOrder());

        for (Entry<Integer, Integer> entry : addressToValue.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        String[] strings = {"I'm", "running", "out", "of", "pangrams!"};
        Arrays.stream(strings).collect(Collectors.joining(",", "[", "]"));

        List<Fruit> fruits = List.of(new WineSapApple(100, 50), new Orange(10));
        Collections.sort(fruits, new FruitPriceComparator());
//        Collections.sort(fruits, new AppleQualityComparator()); compilation error

        List<Apple> apples = List.of(new WineSapApple(100, 50), new WineSapApple(150, 75));
        Collections.sort(apples, new FruitPriceComparator());
        Collections.sort(apples, new AppleQualityComparator());

        Stream.of(1, 2, 3).collect(Collectors.toList());

    }
}

class Foo {
    void foo(String foo) { System.out.println("String"); }
    void foo(Object foo) { System.out.println("Object"); }
}

interface Fruit {
    int getPrice();
}

class Orange implements Fruit {
    int price;

    public Orange(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
interface Apple extends Fruit {
    int getAppleQuality();
}

class WineSapApple implements Apple {

    public int price, quality;

    public WineSapApple(int price, int quality) {
        this.price = price;
        this.quality = quality;
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getAppleQuality() {
        return quality;
    }
}

class AppleQualityComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2) {
        return Integer.compare(a1.getAppleQuality(), a2.getAppleQuality());
    }
}

class FruitPriceComparator implements Comparator<Fruit> {
    public int compare(Fruit f1, Fruit f2) {
        return Integer.compare(f1.getPrice(), f2.getPrice());
    }
}