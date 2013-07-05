package com.group.hackerrank.contests.twitter;

public class DataUtil {
	
	public static String[] getWords(String input){
		return input.split("\\P{Alpha}+");
	}

}
