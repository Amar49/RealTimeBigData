package edu.nyu.xyz.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParserUtil {

	public static String getSelfDefinedJSONString (JSONArray jsonArray) {
		if (jsonArray == null || !(jsonArray instanceof JSONArray)) {
			throw new IllegalArgumentException("Input should be JSONArray typed!");
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < jsonArray.size(); i++) {
			if (i % 10000 == 0) {
				System.out.println("Item number " + i);
			}
			JSONObject object = (JSONObject) jsonArray.get(i);
			if(i == jsonArray.size() - 1) {
				stringBuilder.append(object.toJSONString());
			} else {
				stringBuilder.append(object.toJSONString() + ",\n");
			}
		}
		return stringBuilder.toString();
	}
}
