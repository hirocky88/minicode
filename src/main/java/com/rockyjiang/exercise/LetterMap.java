package com.rockyjiang.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LetterMap {
	private Set<String> combinations;

	Set<String>getResult() {
		return new TreeSet<String>(combinations);
	}
	
	public Set<String>getCombinations(Integer[] nums) {
		combinations = new TreeSet<String>();

		List<List<Character>> charList = numListToCharList(nums);
		genCombination(charList, "", 0);
		
		return combinations;
	}
	
	private void genCombination(List<List<Character>> charList, String word, int i) {		
		if (i == 0) {
			combinations = new TreeSet<String>();
			word = "";
		}
		
		if (i == charList.size()) {
			if (word != null && word.length() > 0) {
				combinations.add(word.toString());
			}
			return;
		}
		
		if (charList.get(i).size() == 0) {
			// Double check, make sure there's no empty list
			genCombination(charList, word, i+1);
		} else {
			for (Character c : charList.get(i)) {
				genCombination(charList, word+c, i+1);
			}						
		}
	}

	public Integer[] readNumbersFromConsole() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> numList = new LinkedList<Integer>();
		System.out.println("Plese input integers seperated by \",\":");

		try {
			String[] numStr = br.readLine().split(",");
			
			for (String s : numStr) {
				s = s.trim();
				if (s.length() > 0) {
					numList.add(Integer.parseInt(s));					
				}
			}
			
		} catch (IOException e) {
			System.out.println("IOException in readNumbersFromConsole()");
		} catch (NumberFormatException e) {
			System.out.println("Wrong interger: " + e.getLocalizedMessage());
		}
		
		System.out.println("The integers are: " + numList + "\n");

		return convertIntegers(numList);
	}
	
	public static Integer[] convertIntegers(List<Integer> integers)
	{
	    Integer[] ret = new Integer[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++){
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}

	
	
	public List<List<Character>>numListToCharList(Integer[] numList) {
		List<List<Character>> charList = new LinkedList<List<Character>>();
		for (int i : numList) {
			List<Character> chars = numToChars(i);

			// DONOT add empty chars
			if (chars.size() > 0) { 
				charList.add(chars);				
			}
		}
		return charList;
	}
	
	public List<Character> numToChars(int i) {
		List<Character> charList = new LinkedList<Character>();
		
		while (i > 0) {
			List<Character> tmp = digitToChars(i%10);
			tmp.addAll(charList);
			
			charList = tmp;
			i /= 10;
		}
		return charList;
	}
	
	private List<Character> digitToChars(int i) {
		final String[] letterMap = {
				"", 	// 0
				"", 	// 1
				"abc", 	// 2
				"def", 	// 3
				"ghi", 	// 4
				"jkl", 	// 5
				"mno", 	// 6
				"pqrs", // 7
				"tuv", 	// 8
				"wxyz"	// 9
		};
		
		List<Character> charList = new LinkedList<Character>();
		if (i >= 0 && i <= 9) {
			 for (char c : letterMap[i].toCharArray()) {
				 charList.add(c);
			 }
		}
		
		return charList;
	}
}
