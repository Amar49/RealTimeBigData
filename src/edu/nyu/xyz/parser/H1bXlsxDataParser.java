package edu.nyu.xyz.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
public class H1bXlsxDataParser {
 
	public static void main(String[] args)
  {
		String inputFilePathBase = "/Users/longyang/git/RealTimeBigData/data/h1b/xlsx/";
		String outputFilePathBase = "/Users/longyang/git/RealTimeBigData/data/h1b/json/";
		String[] xlsxs = {
				"H1B_2008_1.xlsx", "H1B_2008_2.xlsx", "H1B_2010_1.xlsx", "H1B_2010_2.xlsx", 
				"H1B_2011_1.xlsx", "H1B_2011_2.xlsx", "H1B_2012_1.xlsx", "H1B_2012_2.xlsx", 
				"H1B_2013_1.xlsx", "H1B_2013_2.xlsx", "H1B_2014_1.xlsx", "H1B_2014_2.xlsx"
		};
		String[] jsons = {
				"H1B_2008_1.json", "H1B_2008_2.json", "H1B_2010_1.json", "H1B_2010_2.json", 
				"H1B_2011_1.json", "H1B_2011_2.json", "H1B_2012_1.json", "H1B_2012_2.json", 
				"H1B_2013_1.json", "H1B_2013_2.json", "H1B_2014_1.json", "H1B_2014_2.json"
		};
		
		for(int i = 0; i < xlsxs.length; i++) {
			String xlsx = xlsxs[i];
			String json = jsons[i];
			String inputFilePath = inputFilePathBase + xlsx;
			String outputFilePath = outputFilePathBase + json;
			xlsxToJSON(inputFilePath, outputFilePath);
		}
		
  }
	
	@SuppressWarnings("unchecked")
	public static void xlsxToJSON(String inputFilePath, String outputFilePath) {
		if (inputFilePath == null || inputFilePath.isEmpty() ||
				outputFilePath == null || outputFilePath.isEmpty()) {
			throw new IllegalArgumentException("Both of input and output file path should"
					+ "not be null or empty!");
		}
		
		try {
			FileInputStream file = new FileInputStream(new File(inputFilePath));
      // Create Workbook instance holding reference to .xlsx file
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      // Get first/desired sheet from the workbook
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      /*
       * 1. Get the title as keys
       * 
       * Basically, the keys should be 
       * ["company", "state", "position", "salary", "unit"]
       */
      Row firstRow = sheet.getRow(0);
      List<String> keys = new ArrayList<String>();
      for(Cell cell : firstRow) {
      	keys.add(cell.getStringCellValue());
      }
      System.out.println(keys);
      
      // 2. Generate the JSON Objects and insert them into the JsonArray
      JSONArray resultArray = new JSONArray();
      // 3. Flag to notify whether this is an error data and other statistic data.
      boolean error = false;
      int errorRowNumber = 0;
      int salaryMultiplayer = 1;
      for(int i = 1; i <= sheet.getLastRowNum(); i++) {
      	System.out.println("Row: " + i);
      	Row row = sheet.getRow(i);
      	if(row == null) {
      		errorRowNumber++;
      		continue;
      	}
      	
      	error = false;
      	JSONObject jsonObject = new JSONObject();
      	if(row.getLastCellNum() != keys.size()) {
      		errorRowNumber++;
      		continue;
      	}
      	for(int j = 0; j < row.getLastCellNum(); j++) {
      		Cell cell = row.getCell(j);
      		String key = keys.get(j);
      		String str = "";
      		if(cell == null) {
      			error = true;
      			errorRowNumber++;
      			break;
      		}
      		// construct the string str
      		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
      			str = "" + cell.getNumericCellValue();
      		} else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
      			str = cell.getRichStringCellValue().toString().toLowerCase();
      		}
      		
      		/*
      		 * Deal with the company names and delete all the useless ". Inc."s.
      		 */
      		if("company".equalsIgnoreCase(key)) {
      			String pattern1 = "(^.+)(, inc.$)";
      			String pattern2 = "(^.+)(,inc.$)";
      			String pattern3 = "(^.+)(, inc$)";
      			String pattern4 = "(^.+)(,inc$)";
      			String pattern5 = "(^.+)( inc$)";
      			String pattern6 = "(^.+)(inc$)";
      			String pattern7 = "(^.+)( inc.$)";
      			String pattern8 = "(^.+)(inc.$)";
      			str = str.replaceAll(pattern1, "$1");
      			str = str.replaceAll(pattern2, "$1");
      			str = str.replaceAll(pattern3, "$1");
      			str = str.replaceAll(pattern4, "$1");
      			str = str.replaceAll(pattern5, "$1");
      			str = str.replaceAll(pattern6, "$1");
      			str = str.replaceAll(pattern7, "$1");
      			str = str.replaceAll(pattern8, "$1");
      		}
      		
      		/*
      		 * Salary Convert: converted to year salary
      		 * Year = 12 * Month = 52 * 40 * Hour
      		 */
      		if("unit".equalsIgnoreCase(key)) {
      			switch(str) {
      				case "year":
      				case "yr":
      					salaryMultiplayer = 1;
      					break;
      				case "month":
      				case "mth":
      					salaryMultiplayer = 12;
      					break;
      				case "hour":
      				case "hr":
      					salaryMultiplayer = 52 * 40;
      					break;
      			}
      			continue;
      		}
      		/*
      		 * Deal with the salary part.
      		 */
      		if ("salary".equalsIgnoreCase(key)) {
      			double salary = 0;
      			try {
      				salary = Double.parseDouble(str) * salaryMultiplayer;
      			} catch (NumberFormatException e) {
      				error = true;
      				break;
      			}
      			jsonObject.put(key, (int) salary);
      		} else {
      			jsonObject.put(key, str);
      		}
      	}
      	if (!error) {
      		resultArray.add(jsonObject);
      	}
      }
      
      System.out.println("Error Data Number: " + errorRowNumber);
      System.out.println("All Done!");
      String companyPositionSalaryStr = ParserUtil.getSelfDefinedJSONString(resultArray);
			FileWriter writer = new FileWriter(outputFilePath);
			writer.write(companyPositionSalaryStr);
			writer.flush();
			writer.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}