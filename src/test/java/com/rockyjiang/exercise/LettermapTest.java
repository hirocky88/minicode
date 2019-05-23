package com.rockyjiang.exercise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class LettermapTest extends TestCase {
	LetterMap letterMap = new LetterMap();
	Integer[] inNum1 = {2,3};
	String[] outString1 = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
	
	Integer[] inNum2 = {9};
	String[] outString2 = {"w", "x", "y", "z"};
	
    @Before
    public void before() {
    	System.out.println("Before ===============");    	
    }

    @After
    public void after() {
    	System.out.println("After ===============");
    }
    
    @Test
    public void testCharDictionary() {
    	System.out.println("testNumberToChars");
    	    	
    	Set<String> resultSet = new HashSet<String>();
    	Integer[] singleNum = {0}; 
     	for (int i = 0; i < 10; i++) {
     		singleNum[0] = i;
			resultSet.addAll(letterMap.getCombinations(singleNum));
		}
    	
    	Assert.assertTrue(letterMap.numToChars(0).size() == 0);
    	Assert.assertTrue(letterMap.numToChars(1).size() == 0);
    	Assert.assertTrue(letterMap.numToChars(2).get(0) == 'a');
    	Assert.assertTrue(letterMap.numToChars(9).get(3) == 'z');
    	Assert.assertTrue(resultSet.size() == 26);
    }
    
    @Test
    public void testDataConsistency() {
    	Random random = new Random();

		int i = random.nextInt(10);
		
		// number 3 and 33 maps to the same chars
		List<Character> chars1 = letterMap.numToChars(i);
		List<Character> chars2 = letterMap.numToChars(i*10 + i);
		
    	Assert.assertTrue(chars1.containsAll(chars2));
    }
    
    @Test
    public void testSpecificNumber() {
    	Assert.assertTrue(letterMap.getCombinations(inNum1).containsAll(Arrays.asList(outString1)));
    	Assert.assertTrue(letterMap.getCombinations(inNum2).containsAll(Arrays.asList(outString2)));

    	doesMatch(randNums(inNum1), Arrays.asList(inNum1));
    	doesMatch(randNums(inNum2), Arrays.asList(inNum2));
    }
   
	// Adding 0 or 1 wont change the result
    private List<Integer> randNums(Integer[] in)  {
    	Random random = new Random();
    	List<Integer> randNum = new ArrayList<Integer>(Arrays.asList(in));

    	if (random.nextBoolean()) {
    		randNum.add(random.nextInt(randNum.size()), 0);
    	}
    	
    	if (random.nextBoolean()) {
    		randNum.add(random.nextInt(randNum.size()), 1);
		}
    	
		return randNum;

    }
    
    private void doesMatch(List<Integer> num1, List<Integer> num2) {
    	Set<String> result1 = letterMap.getCombinations(LetterMap.convertIntegers(num1));
    	Set<String> result2 = letterMap.getCombinations(LetterMap.convertIntegers(num2));
    	
    	System.out.println(num1);
    	System.out.println(num2);
    	System.out.println(result1);
    	System.out.println(result2);
    	Assert.assertTrue(result1.containsAll(result2));
    	Assert.assertTrue(result2.containsAll(result1));
    }
}
