package edu.nyu.xyz.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cedarsoftware.util.io.JsonWriter;

public class LinkedInDataParser {

	public static void main(String[] args) {
		companyPositionSkills(
				"/Users/longyang/git/RealTimeBigData/crawler/linkedInScraper/linkedIn/items.json", 
				"/Users/longyang/git/RealTimeBigData/crawler/linkedInScraper/linkedIn/items_output.json");
	}
	
	/**
	 * Method used to get the company, position and skills of a LinkedIn member from his profile,
	 * and we will get rid of incomplete data, such as one of the three fields is empty.
	 * 
	 * @param inputFilePath path for the input file from local storage.
	 * @param outputFilePath path for output file and the output file is as the following format:
	 * {
	 * 	{@code ParserConstants#COMPANY} : "",
	 * 	{@code ParserConstants#POSITION} : "",
	 * 	{@code ParserConstants#SKILLS} : []
	 * }
	 * 
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static void companyPositionSkills(String inputFilePath, String outputFilePath) {
		if (inputFilePath == null || inputFilePath.isEmpty() ||
				outputFilePath == null || outputFilePath.isEmpty()) {
			throw new IllegalArgumentException("The inputFile and outputFile path should "
					+ "not be null or empty!");
		}
		JSONParser parser = new JSONParser();
		
		try {
			FileReader inputFile = new FileReader(inputFilePath);
			Object object = parser.parse(inputFile);
			JSONArray inputJsonArray = new JSONArray();
			JSONArray outputJsonArray = new JSONArray();
			
			if (object instanceof JSONArray) {
				inputJsonArray = (JSONArray)object;
			} else {
				throw new Exception("Input LinkedIn data should be a Json Array!");
			}
			
			for (int i = 0; i < inputJsonArray.size(); i++) {
				Object profile = inputJsonArray.get(i);
				JSONObject jsonProfile = new JSONObject();
				JSONObject outputJsonProfile = new JSONObject();
				
				if (profile instanceof JSONObject) {
					jsonProfile = (JSONObject)profile;
				} else {
					throw new Exception("Items inside LinkedIn Json Data should be JSONObject!");
				}
				JSONArray currentCompany = (JSONArray)jsonProfile.get(ParserConstants.CURRENT_COMPANY);
				JSONArray currentPosition = (JSONArray)jsonProfile.get(ParserConstants.CURRENT_POSITION);
				JSONArray skills = (JSONArray)jsonProfile.get(ParserConstants.SUMMARYSPECIALTIES);
				
				if (currentCompany != null && currentCompany.size() == 1 ) {
					outputJsonProfile.put(ParserConstants.COMPANY, (String)currentCompany.get(0));
				} else {
					continue;
				}
				if (currentPosition != null && currentPosition.size() == 1) {
					outputJsonProfile.put(ParserConstants.POSITION, (String)currentPosition.get(0));
				} else {
					continue;
				}
				if (skills != null && !skills.isEmpty()) {
					outputJsonProfile.put(ParserConstants.SKILLS, skills);
				} else {
					continue;
				}
				
				outputJsonArray.add(outputJsonProfile);
			}
			System.out.println("Done! And the size of final good LinkedIn profiles is: " + 
					outputJsonArray.size());
			String niceFormatJsonAllPositions = JsonWriter.formatJson(outputJsonArray.toJSONString());
			FileWriter outputFileWriter = new FileWriter(outputFilePath);
			outputFileWriter.write(niceFormatJsonAllPositions);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
