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

public class GlassDoorDataParser {

	public static void main(String[] args) {
		getCompanyNamesAndPosition("/Users/longyang/git/RealTimeBigData/data/glassdoor/company.json", 
				"/Users/longyang/git/RealTimeBigData/data/glassdoor/company_output.json", 
				"Information Technology");
	} 
	
	/**
	 * Method used to parse the input JSON typed file and get only company names and their 
	 * regarding position and salaries.
	 * @param inputFilePath input file path, and the input file format is as following:
	 * [ 
	 * 	{
	 * 		"name": "Google", 
	 * 		"info": 
	 * 			{
	 * 				"satisfaction": {},
	 * 				"ceo": {},
	 * 				"meta": {},
	 * 				"salary": 
	 * 					[
	 * 						{
	 * 							"position": "SDE",
	 * 							"range": [100000, 110000]
	 * 						},
	 * 						{
	 * 							"position": "QA",
	 * 							"range": [80000, 100000]
	 * 						}
	 * 					]
	 * 			}
	 * 	}
	 * ]
	 * @param outputFilePath output file path, and the output file format is as following:
	 * [
	 * 	{
	 * 		"name": "Google",
	 * 		"position_number": 20,
	 * 		"position": // make it easy for do people search on LinkedIn.
	 * 			[
	 * 				"SDE",
	 * 				"QA"
	 * 			],
	 * 		"position_range": 
	 * 			{
	 * 				"SDE": [100000, 110000],
	 * 				"QA": [80000, 100000]
	 * 			}
	 * 	}
	 * ]
	 * @param industry determine which industry we are focusing on, and it can be "All" which means
	 * you want all industry's data.
	 */
	@SuppressWarnings("unchecked")
	public static void getCompanyNamesAndPosition(String inputFilePath, String outputFilePath, 
			String industry) {
		if(	inputFilePath == null || inputFilePath.isEmpty() ||
				outputFilePath == null || outputFilePath.isEmpty() ||
				industry == null || industry.isEmpty()) {
			throw new IllegalArgumentException("inputFilePath, outputFilePath and industry "
					+ "should not be null or empty!");
		}
		JSONParser parser = new JSONParser();
		
		try {
			int illegalData = 0;
			FileReader inputFile = new FileReader(inputFilePath);
			Object object = parser.parse(inputFile);
			JSONArray inputJsonArray = (JSONArray) object;
			JSONArray outputJsonArray = new JSONArray();
			
			for(int i = 0; i < inputJsonArray.size(); i++) {
				// 1. Get all the information from "inputJsonObject"
				JSONObject inputJsonObject = (JSONObject) inputJsonArray.get(i);
				String name = (String) inputJsonObject.get(ParserConstants.NAME);
				System.out.println("Number " + i + ": " + name + ": processing...");
				JSONObject info = new JSONObject();
				if( inputJsonObject.get(ParserConstants.INFO) instanceof JSONObject) {
					info = (JSONObject) inputJsonObject.get(ParserConstants.INFO); 
				} else {
					continue;
				}
				JSONObject info_meta = (JSONObject) info.get(ParserConstants.META);
				String info_meta_industry = (String) info_meta.get(ParserConstants.INDUSTRY);
				if( !industry.equalsIgnoreCase(ParserConstants.ALL) && 
						!industry.equalsIgnoreCase(info_meta_industry)) {
					continue;
				}
				JSONArray info_salary = (JSONArray) info.get(ParserConstants.SALARY);
				
				// 2. Construct output object
				JSONObject outputJsonObject = new JSONObject();
				outputJsonObject.put(ParserConstants.NAME, name);
				if(info_salary.size() <= 0) {
					illegalData = illegalData + 1;
					continue;
				}
				outputJsonObject.put(ParserConstants.POSITION_NUMBER, info_salary.size()); 
				JSONArray name_position = new JSONArray();
				JSONObject position_range = new JSONObject();
				for(int j = 0; j < info_salary.size(); j++) {
					JSONObject infoSalaryObject = (JSONObject) info_salary.get(j);
					String info_salary_position = (String) infoSalaryObject.get(ParserConstants.POSITION);
					String positionStr = info_salary_position;
					name_position.add(positionStr);
					JSONArray info_salary_range = (JSONArray) infoSalaryObject.get(ParserConstants.RANGE);
					position_range.put(positionStr, info_salary_range);
				}
				outputJsonObject.put(ParserConstants.POSITION, name_position);
				outputJsonObject.put(ParserConstants.POSITION_RANGE, position_range);
				outputJsonArray.add(outputJsonObject);
			}
			System.out.println("Done! And here comes the statistics: ");
			System.out.println("Illegal Data Number: " + illegalData);
			System.out.println("Total result company number: " + outputJsonArray.size());
			String niceFormatJson = JsonWriter.formatJson(outputJsonArray.toJSONString());
			FileWriter fileWriter = new FileWriter(outputFilePath);
			fileWriter.write(niceFormatJson);
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
