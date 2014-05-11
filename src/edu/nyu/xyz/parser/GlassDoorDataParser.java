package edu.nyu.xyz.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GlassDoorDataParser {

	public static void main(String[] args) {
		String inputFilePath = 
				"/Users/longyang/git/RealTimeBigData/data/glassdoor/company/glassdoor.json";
		String companyNamesPositionsOuputFilePath = 
				"/Users/longyang/git/RealTimeBigData/data/glassdoor//CompanyIndustry.json";
		String companyAllPositionsOutputFilePath = 
				"/Users/longyang/git/RealTimeBigData/data/glassdoor/company_all_position.json";
		String industry = "Information Technology";
		String companyPositionSalaryOutputFilePath = 
				"/Users/longyang/git/RealTimeBigData/data/glassdoor/companyPositionSalary.json";
		
		getCompanyNamesAndPosition(inputFilePath, companyNamesPositionsOuputFilePath, 
				companyAllPositionsOutputFilePath, ParserConstants.ALL);
//		companyPositionSalary(companyNamesPositionsOuputFilePath, companyPositionSalaryOutputFilePath);
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
	 * @param outputPositionFilePath output file path for all position data.
	 * @param industry determine which industry we are focusing on, and it can be "All" which means
	 * you want all industry's data.
	 */
	@SuppressWarnings("unchecked")
	public static void getCompanyNamesAndPosition(String inputFilePath, String outputFilePath, 
			String outputPositionFilePath, String industry) {
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
//			JSONArray allPositionsArray = new JSONArray();
//			Set<String> allPositionsSet = new HashSet<String>();
			
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
				String info_meta_industry = (String) info_meta.get(ParserConstants.INPUT_INDUSTRY);
//				if( !industry.equalsIgnoreCase(ParserConstants.ALL) && 
//						!industry.equalsIgnoreCase(info_meta_industry)) {
//					continue;
//				}
				JSONArray info_salary = (JSONArray) info.get(ParserConstants.SALARY);
				
				// 2. Construct output object
				JSONObject outputJsonObject = new JSONObject();
				outputJsonObject.put(ParserConstants.COMPANY, name.toLowerCase());
				if (info_meta_industry == null || info_meta_industry.isEmpty()) {
					continue;
				} else {
					outputJsonObject.put(ParserConstants.INDUSTRY, info_meta_industry.toLowerCase());
				}
				
				if(info_salary.size() <= 0) {
					illegalData = illegalData + 1;
					continue;
				}
//				outputJsonObject.put(ParserConstants.POSITION_NUMBER, info_salary.size()); 
//				JSONArray name_position = new JSONArray();
//				JSONObject position_range = new JSONObject();
//				for(int j = 0; j < info_salary.size(); j++) {
//					JSONObject infoSalaryObject = (JSONObject) info_salary.get(j);
//					String info_salary_position = (String) infoSalaryObject.get(ParserConstants.POSITION);
//					allPositionsSet.add(info_salary_position);
//					String positionStr = info_salary_position;
//					name_position.add(positionStr);
//					JSONArray info_salary_range = (JSONArray) infoSalaryObject.get(ParserConstants.RANGE);
//					position_range.put(positionStr, info_salary_range);
//				}
//				outputJsonObject.put(ParserConstants.POSITION, name_position);
//				outputJsonObject.put(ParserConstants.POSITION_RANGE, position_range);
				outputJsonArray.add(outputJsonObject);
			}
			System.out.println("Done! And here comes the statistics: ");
			System.out.println("Illegal Data Number: " + illegalData);
			System.out.println("Total result company number: " + outputJsonArray.size());
			// 3.1. Output all positions data into certain file.
//			allPositionsArray.addAll(allPositionsSet);
//			String niceFormatJsonAllPositions = JsonWriter.formatJson(allPositionsArray.toJSONString());
			String companyIndustry = ParserUtil.getSelfDefinedJSONString(outputJsonArray);
			FileWriter allPositionsFileWriter = new FileWriter(outputFilePath);
			System.out.println(companyIndustry);
			System.out.println(outputFilePath);
			allPositionsFileWriter.write(companyIndustry);
			allPositionsFileWriter.flush();
			allPositionsFileWriter.close();
			inputFile.close();
			// 3.2. Output all companies data into certain file.
//			String niceFormatJson = JsonWriter.formatJson(outputJsonArray.toJSONString());
//			FileWriter fileWriter = new FileWriter(outputFilePath);
//			inputFile.close();
//			fileWriter.write(niceFormatJson);
//			fileWriter.flush();
//			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void companyPositionSalary(String inputFilePath, String outputFilePath) {
		if (inputFilePath == null || inputFilePath.isEmpty() ||
				outputFilePath == null || outputFilePath.isEmpty()) {
			throw new IllegalArgumentException("InputFilePath and OutputFilePath should not be"
					+ "null or empty!");
		}
		
		JSONParser parser = new JSONParser();
		
		try {
			FileReader inputFile = new FileReader(inputFilePath);
			Object object = parser.parse(inputFile);
			JSONArray inputJsonArray = (JSONArray) object;
			JSONArray outputJsonArray = new JSONArray();
			
			for (int i = 0; i < inputJsonArray.size(); i++) {
				// 1. Get all the information from the input file
				JSONObject inputObject = (JSONObject) inputJsonArray.get(i);
				String companyName = (String) inputObject.get(ParserConstants.COMPANY);
				JSONArray positions = (JSONArray) inputObject.get(ParserConstants.POSITION);
				JSONObject salaryRanges = (JSONObject) inputObject.get(ParserConstants.POSITION_RANGE);
				
				// 2. Generate the output to store into OutputFilePath
				for (int j = 0; j < positions.size(); j++) {
					String position = (String) positions.get(j);
					JSONArray salaryRange = (JSONArray) salaryRanges.get(position);
					int salaryLow = (int)((long) salaryRange.get(0));
					int salaryHigh = (int)((long) salaryRange.get(1));
					int salary = (salaryLow + salaryHigh) >>> 1;
					JSONObject outputObject = new JSONObject();
					outputObject.put(ParserConstants.COMPANY, companyName);
					outputObject.put(ParserConstants.POSITION, position);
					outputObject.put(ParserConstants.SALARY, salary);
					outputJsonArray.add(outputObject);
				}
			}
			System.out.println("Done! And here comes the statistics: ");
			System.out.println("Total result company number: " + outputJsonArray.size());
			String companyPositionSalaryStr = ParserUtil.getSelfDefinedJSONString(outputJsonArray);
			FileWriter writer = new FileWriter(outputFilePath);
			writer.write(companyPositionSalaryStr);
			writer.flush();
			writer.close();
			inputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
