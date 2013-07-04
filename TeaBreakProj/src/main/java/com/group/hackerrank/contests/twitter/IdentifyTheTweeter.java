package com.group.hackerrank.contests.twitter;

import java.io.File;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





class TweetClassifier {
	
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
		// should be one per tweet
		//categoryCounts.put(category, categoryCounts.get(category)+1);
		
	}
	
	
	private int getCategoryCountForWord(String word, String category){
		return ((wordmap.get(word)==null ? 0 : wordmap.get(word).get(category))== 0) ? 0 : (wordmap.get(word)==null ? 0 : wordmap.get(word).get(category)) ;
	}
	
	
	private int getOverallCatCountForWord(String word){
		
		Map<String, Integer> map = wordmap.get(word);
		if (null==map)
			return 0;
		else{
			int sum =0;
			for(String s : map.keySet()){
				sum += map.get(s); 
			}
			
			return sum;
		}
		
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
		System.out.println("\n-----------------------------------------------------------------------------");
		System.out.println("## Labels Count ##");
		for(String cat : categoryCounts.keySet()){
			System.out.print(cat+":"+categoryCounts.get(cat)+",");		
		}
		System.out.println();
		System.out.println("\n-----------------------------------------------------------------------------");
		
	}
	
	
	
	public void trainClassifier(String tweet, String category){
		String[] words = DataUtil.getWords(tweet);
		
		for(String word : words){
			if(word.length()>2 && word.length()<15 && !DataUtil.isStopWord(word)){
				
				addWordToClassifier(word, category);
			}
		}
		
		// increment the cat count for this tweet
		categoryCounts.put(category, categoryCounts.get(category)+1);
		
		//printClassifier();
	}
	
	
	
	
	private float getWordProbabilityForGivenCat(String word, String category){
		float basicprob =  getCategoryCountForWord(word, category)/(float)getCategoryCount(category);
		
		/** Normalize probability now **/
		float weight = 1.0f;
		float assumedProb = 0.5f;
		int allWordOccurances = getOverallCatCountForWord(word);
		
		float normalizedProb = ((weight*assumedProb)+(allWordOccurances*basicprob))/(weight+allWordOccurances);
		return normalizedProb;
		
	}
	
	
	
	private float getTweetProbabilityForGivenCat(String tweet, String category){
		String[] words = DataUtil.getWords(tweet);
		
		float overallProb =1;
		boolean changed= false;
		for(String word: words){
			
			/** Small modification to word if it contains direct key words **/
			if(word.contains("google")){
				word = "google";
			}
			else if(word.contains("bill") || word.contains("gates")){
				word = "bill";
			}
			else if(word.contains("barack") || word.contains("obama")){
				word="obama";
			}
			else if(word.contains("james")){
				word = "kingjames";
			}
			
			float prob = getWordProbabilityForGivenCat(word, category);
			//System.out.println("word:"+word+",prob:"+prob);
			if(prob != 0){
				overallProb *= getWordProbabilityForGivenCat(word, category);
				changed=true;
			}
		}
		//System.out.println("overall for given cat:"+overallProb);
		return changed ? overallProb : 0;
	}
	
	
	
	private float getProbabilityForGivenDocument(String category){
		return (getCategoryCount(category)/(float)getAllCategoriesCount());
	}
	
	
	
	private float bayseanProb(String tweet, String category){
		//System.out.println("Baysean prob, CAT:"+category);
		
		return (getTweetProbabilityForGivenCat(tweet, category)*getProbabilityForGivenDocument(category));
	}
	
	
	
	public String classifyBaysean(String tweet){
		
		tweet = tweet.toLowerCase();
		
		String bestCat= null;
		float bestProb= Integer.MIN_VALUE;
		float currentProb;
		//System.out.println("OVERALL Prob:");
		for(String category : categoryCounts.keySet()){
			currentProb = bayseanProb(tweet, category);
			//System.out.println("cat:"+category+",finalprob for whole tweet:"+currentProb);
			
			
			if(currentProb > bestProb){
				bestProb = currentProb;
				bestCat = category;
			}
		}
		return bestCat;
		
	}
 
}




class DataUtil {
	
	private static final Map<String, Integer> STOPWORD_MAP;
	
	private static final String[] STOP_WORDS = {"a", "about", "above", "above", "across", "after", "afterwards", "again", 
												"against", "all", "almost", "alone", "along", "already", "also","although",
												"always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", 
												"any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  
												"at", "back","be","became", "because","become","becomes", "becoming", "been", "before", 
												"beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", 
												"both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", 
												"couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", 
												"each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", 
												"even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", 
												"fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", 
												"found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", 
												"hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", 
												"hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", 
												"inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", 
												"latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", 
												"mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", 
												"namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", 
												"nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", 
												"onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own",
												"part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", 
												"seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", 
												"six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", 
												"somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", 
												"themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", 
												"thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", 
												"through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", 
												"twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", 
												"we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", 
												"whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", 
												"who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", 
												"yet", "you", "your", "yours", "yourself", "yourselves", "the"};
	
	static{
		STOPWORD_MAP = new HashMap<String, Integer>();
		for(String word : STOP_WORDS){
			STOPWORD_MAP.put(word, 1);
		}
		
	}
	
	
	public static String removeUrl(String input)
    {
        String output = input;
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(output);
        int i=0;
        while (m.find()) {
        	output = output.replaceAll(m.group(i),"").trim();
            i++;
        }
        
        return output;
    }
	
	
	public static String[] getWords(String input){
		
		// convert to lower case
		input = input.toLowerCase();
		
		// remove braces
		input = input.replace("(", "");
		input = input.replace(")", "");
		
		// remove URL's
		input = removeUrl(input);		
		
		// split by all non alphabetic characters
		return input.split("\\P{Alpha}+");
	}
	
	
	public static boolean isStopWord(String word){
		return (STOPWORD_MAP.get(word)==null) ? false : true;
	}
	
	


}




public class IdentifyTheTweeter {
	
	
	public void init() throws FileNotFoundException{
		File file = new File("./trainingdata.txt");
		Scanner sc = new Scanner(file);
		String line;
		String tweet, label;
		int N;
		TweetClassifier classifier = new TweetClassifier();
		
		N = Integer.parseInt(sc.nextLine());		
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			//System.out.println("## TRAINING: "+line);
			label = line.substring(0, line.indexOf(" "));
			tweet = line.substring(line.indexOf(" "), line.length());
			
			classifier.trainClassifier(tweet, label);
			
		}
		
		
		
		//classifier.printClassifier();
		/** End of Training **/
		
		/** Begin Test, get user Tweet & predict class **/
		
		sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());		
		
		while(sc.hasNextLine()){
			String inputTweet = sc.nextLine();
			if(null!=inputTweet && inputTweet.length()>0){
				System.out.println(classifier.classifyBaysean(inputTweet));
			}
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		new IdentifyTheTweeter().init();
		
	}

}
