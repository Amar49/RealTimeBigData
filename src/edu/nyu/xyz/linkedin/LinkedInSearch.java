package edu.nyu.xyz.linkedin;

import java.util.EnumMap;
import java.util.Map;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.schema.People;
import com.google.code.linkedinapi.schema.Person;

public class LinkedInSearch {
	private static final String consumerKeyValue = "77usflo8k00xwy";
	private static final String consumerSecretValue = "YxqfrxnbYBqPNG5X";
	private static final String accessTokenValue = "0b64e5e7-80ad-4102-8ece-83f3b0de042b";
	private static final String tokenSecretValue = "744c6dca-895e-48aa-af82-3e85a0126b9a";
	
	public static void main(String[] args) {
		
		final LinkedInApiClientFactory factory = 
				LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
		final LinkedInApiClient client = 
				factory.createLinkedInApiClient(accessTokenValue, tokenSecretValue);
		
		Map<SearchParameter, String> searchParameters = new EnumMap<SearchParameter, String>(SearchParameter.class);
		String keywords = "Yahoo SDE";
		searchParameters.put(SearchParameter.KEYWORDS, keywords);
//		searchParameters.put(SearchParameter.COMPANY_NAME, companyName);

		People people = client.searchPeople(searchParameters);
		System.out.println("Total search result:" + people.getCount());
		for (Person person : people.getPersonList()) {
		        System.out.println(person.getId() + ":" + person.getFirstName() + " " + 
		        		person.getLastName() + ":" + person.getPublicProfileUrl());
		}
		
//		Person profile = client.getProfileById("x5teEKX1MX");
//		System.out.println("Name:" + profile.getFirstName() + " " + profile.getLastName());
//		System.out.println("URL:" + profile.getPublicProfileUrl());
//		System.out.println("Location:" + profile.getLocation());
//		System.out.println("Industry:" + profile.getIndustry());
//		System.out.println("Picture:" + profile.getPictureUrl());
		
	}
}
