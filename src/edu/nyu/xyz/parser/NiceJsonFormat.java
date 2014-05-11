package edu.nyu.xyz.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cedarsoftware.util.io.JsonWriter;

public class NiceJsonFormat {

	public static void main(String[] args) {
		niceFormat(args[0], args[1]);
	}
	
	public static void niceFormat(String inputFilePath, String outputFilePath) {
		if(	inputFilePath == null || inputFilePath.isEmpty() ||
				outputFilePath == null || outputFilePath.isEmpty()) {
			throw new IllegalArgumentException("inputFilePath, outputFilePath "
					+ "should not be null or empty!");
		}
		JSONParser parser = new JSONParser();
		
		try {
			FileReader inputFile = new FileReader(inputFilePath);
			Object object = parser.parse(inputFile);
			JSONArray inputJsonArray = (JSONArray) object;
			
			String niceFormatJson = JsonWriter.formatJson(inputJsonArray.toJSONString());
			FileWriter allPositionsFileWriter = new FileWriter(outputFilePath);
			allPositionsFileWriter.write(niceFormatJson);
			allPositionsFileWriter.flush();
			allPositionsFileWriter.close();
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
