package com.group.hackerrank.contests.twitter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;




public class TweetClassifier {
	
	private Map<String, Map<String, Integer>> wordmap;
	private Map<String, Integer> categoryCounts;
	
	String[] ALL_LABELS = {"justinbieber", "BillGates", "KingJames", "google", "BarackObama"};
	
	public TweetClassifier(){		
		wordmap = new HashMap<String, Map<String,Integer>>();
		
		Map<String, Integer> blankMap = new HashMap<String, Integer>();
		for(String label : ALL_LABELS){
			blankMap.put(label, 0);
		}
		categoryCounts = blankMap;
		
	}
	
	
	/**
	 * Public method that will add given word n category to classifier.
	 * This will be used iteratively by the train method for all the tweets. 
	 * 
	 * @param word
	 * @param category
	 */
	private void addWordToClassifier(String word, String category){
		
		word= word.toLowerCase();
		
		if(null == wordmap.get(word)){
			Map<String, Integer> blankMap = new HashMap<String, Integer>();
			for(String label : ALL_LABELS){
				blankMap.put(label, 0);
			}
			blankMap.put(category, 1);
			
			wordmap.put(word, blankMap);			
		}
		else{
			wordmap.get(word).put(category,  wordmap.get(word).get(category)+1);			
		}
		
		/** also increment the total category count **/
		
		categoryCounts.put(category, categoryCounts.get(category)+1);
		
	}
	
	
	private int getCategoryCountForWord(String word, String category){
		return ((wordmap.get(word)==null ? 0 : wordmap.get(word).get(category))== 0) ? 0 : (wordmap.get(word)==null ? 0 : wordmap.get(word).get(category)) ;
	}
	
	private int getCategoryCount(String category){
		return categoryCounts.get(category)==null ? 0 : categoryCounts.get(category);
	}
	
	
	/** This is also equal to the total number of words processed **/
	private int getAllCategoriesCount(){
		int sum = 0;
		
		for(String cat : categoryCounts.keySet()){
			sum += categoryCounts.get(cat);
		}
		
		return sum;
	}
	
	
	
	/**
	 * Util method to print the status of the classifier.
	 */
	public void printClassifier(){
		
		System.out.println("## Word Map ##");
		for(String word : wordmap.keySet()){
			System.out.println();
			System.out.println("==> word:"+word);
			
			for(String cat : wordmap.get(word).keySet()){
				System.out.print("("+cat+":"+wordmap.get(word).get(cat)+")");
			}
			
		}
		System.out.println("-----------------------------------------------------------------------------");
		
	}
	
	
	
	public void trainClassifier(String tweet, String category){
		String[] words = DataUtil.getWords(tweet);
		
		for(String word : words){
			if(word.length()>2 && word.length()<15){
				addWordToClassifier(word, category);
			}
		}
		
		//printClassifier();
	}
	
	
	
	
	private float getWordProbabilityForGivenCat(String word, String category){
		return getCategoryCountForWord(word, category)/getCategoryCount(category);
	}
	
	
	
	private float getProbabilityForGivenCat(String tweet, String category){
		String[] words = DataUtil.getWords(tweet);
		
		float overallProb =1;
		for(String word: words){
			overallProb *= getWordProbabilityForGivenCat(word, category);
		}
		
		return overallProb;
	}
	
	
	
	private float getProbabilityForGivenDocument(String category){
		return getCategoryCount(category)/getAllCategoriesCount();
	}
	
	
	
	private float bayseanProb(String tweet, String category){
		return getProbabilityForGivenCat(tweet, category)*getProbabilityForGivenCat(tweet, category);
	}
	
	
	
	public String classifyBaysean(String tweet){
		
		// TODO: Iterate over all Categories n getting all the probabilities for all cat's
		// The cat with best prob wins
		
		String bestCat= null;
		float bestProb= Integer.MIN_VALUE;
		float currentProb;
		for(String category : categoryCounts.keySet()){
			currentProb = bayseanProb(tweet, category);
			
			if(currentProb > bestProb){
				bestProb = currentProb;
				bestCat = category;
			}
		}
		
		return bestCat + ":" + bestProb;
		
	}
 
}
