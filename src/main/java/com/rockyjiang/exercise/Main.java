package com.rockyjiang.exercise;

public class Main {
	public static void main(String[] args) {

		LetterMap letterMap = new LetterMap();
		Integer[] nums = letterMap.readNumbersFromConsole();
		
		System.out.print("Word Combination: " + letterMap.getCombinations(nums));
	}
}