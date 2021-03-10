package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	
	public static int[] solution(int k, int m, int[] numbers) {
		List<Integer> leaders = new ArrayList<>();
		for(int index = 0; index <= numbers.length - k; index++) {
			int[] copiedSegmentNumbers = Arrays.copyOfRange(numbers, index, k+index);
			int[] incrementedSegmentNumbers = IntStream.of(copiedSegmentNumbers).map(number -> number + 1).toArray();
			int[] modifiedNumbers = replaceForRange(Arrays.copyOf(numbers, numbers.length), incrementedSegmentNumbers, index, k+index);
			leaders.addAll(getLeaders(modifiedNumbers));
		}
		return leaders.stream().mapToInt(leader-> leader).toArray();
	}	
	
	private static List<Integer> getLeaders(int[] numbers) {
		Map<Integer, Integer> map = getMapWithNumberCount(numbers);

		List<Integer> leaders = new ArrayList<>();
		map.entrySet().stream().filter(entry -> entry.getValue() > numbers.length / 2).forEach(entry -> {
			leaders.add(entry.getKey());			
		});
		
		return leaders;
	}
	
	private static Map<Integer, Integer> getMapWithNumberCount(int[] numbers) {
		Map<Integer, Integer> map = new HashMap<>();
		IntStream.of(numbers).forEach(number -> {
			map.put(number, getNumberCount(map, number));
		});
		return map;
	}
	
	private static int getNumberCount(Map<Integer, Integer> map, int number) {
		int count = 1;
		if (map.containsKey(number)) {
			count = map.get(number) + 1;
		}
		return count;	
	}

	private static int[] replaceForRange(int[] original, int[] target, int from, int to) {
		int targetIndex = 0;
		for(int originalIndex = from; originalIndex <= to - 1; originalIndex++) {
			original[originalIndex] = target[targetIndex];
			targetIndex++;
		}
		return original;
	}
	

}
