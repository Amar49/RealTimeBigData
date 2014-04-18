package edu.nyu.cs.aeoi.twitterapi;

import org.apache.log4j.Logger;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class DemoTestGetTweets {
	private static Logger log = Logger.getLogger(DemoTestGetTweets.class
			.getName());

	public static void main(String[] args) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();

		Query query = new Query("software engineer salary");
		QueryResult result = twitter.search(query);
		for (Status status : result.getTweets()) {
			log.info("@" + status.getUser().getScreenName() + ":"
					+ status.getText());
		}
	}
}
