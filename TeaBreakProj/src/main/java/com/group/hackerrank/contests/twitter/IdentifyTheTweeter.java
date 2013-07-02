package com.group.hackerrank.contests.twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IdentifyTheTweeter {
	
	
	public void init() throws FileNotFoundException{
		File file = new File("C:/Users/yash.sharma/git/TeaBreakProb/trainingdata.txt");
		Scanner sc = new Scanner(file);
		String line;
		String tweet, label;
		int N;
		TweetClassifier classifier = new TweetClassifier();
		
		if(sc.hasNextLine()){
			N = Integer.parseInt(sc.nextLine());
		}
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			System.out.println("## TRAINING: "+line);
			label = line.substring(0, line.indexOf(" "));
			tweet = line.substring(line.indexOf(" "), line.length());
			
			classifier.trainClassifier(tweet, label);
			
		}
		classifier.printClassifier();
		/** End of Training **/
		
		/** Begin Test, get user Tweet & predict class **/
		
		sc = new Scanner(System.in);
		
		while(sc.hasNextLine()){
			System.out.println(classifier.classifyBaysean(sc.nextLine()));
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		new IdentifyTheTweeter().init();
		
	}

}
