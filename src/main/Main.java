package main;

import java.util.stream.IntStream;

public class Main {
	
	public static void main(String[] args) {
		int k = 3;
		int m = 5;
		int[] numbers = {2, 1, 3, 1, 2, 2, 3};
		IntStream.of(Solution.solution(k, m, numbers)).forEach(System.out::println);
	}
}
