package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	static class A {

		@Override
		public boolean equals(Object o) {

			if (o.getClass() == this.getClass()) {
				return true;
			}
			return false;
		}
	}

	static class B extends A {

		@Override
		public boolean equals(Object o) {

			if (super.equals(o)) {
				return true;
			}
			return false;
		}
	}
	public static void main(String[] args) {
//		B b1 = new B();
//		B b2 = new B();
//		System.out.println(b1.equals(b2));

		Queue<Boolean> qq = new ArrayBlockingQueue<>(10);
		for(Boolean element : qq) {
			System.out.println(element);
		}

		System.out.println(Stream.of("one", "two", "three", "four")
								 .filter(e -> e.length() > 3)
								 .peek(e -> System.out.println("After Filter: " + e))
								 .map(String::toUpperCase)
								 .peek(e -> System.out.println("After Mapping: " + e))
								 .collect(Collectors.toList()));


	}
}
