package edu.nyu.xyz.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CountTheNumberOfJSONData {
	
	public static void main(String[] args) {
		int size = jsonArraySize(
				"/Users/longyang/git/RealTimeBigData/data/linkedin/linkedin/out/LinkedInCleanData.json");
		System.out.println("The size of the input json array is " + size);
	}
	
	public static int jsonArraySize(String inputFilePath) {
		if(inputFilePath == null || inputFilePath.isEmpty()) {
			throw new IllegalArgumentException("Input file path should not be null or empty!");
		}
		JSONParser parser = new JSONParser();
		
		try {
			FileReader reader = new FileReader(inputFilePath);
			Object object = parser.parse(reader);
			JSONArray jsonArray = new JSONArray();
			if (object instanceof JSONArray) {
				jsonArray = (JSONArray) object;
				return jsonArray.size();
			} else {
				throw new RuntimeException("Input file should contain all JSONArray typed data!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
