package edu.nyu.cs.aeoi.twitterapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * @author Jing.Xia
 * 
 *         Remember to remove the twitter4j information for oath login.
 */
public class DemoTestOath {
	private static Logger log = Logger.getLogger(DemoTestOath.class.getName());

	public static void main(String[] args) throws TwitterException, IOException {
		log.info("Hello this is an info message");
		Twitter twitter = TwitterFactory.getSingleton();
		// Put my API key here
		twitter.setOAuthConsumer("", "");
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			log.info("Open the following URL and grant access to your account:");
			log.info(requestToken.getAuthorizationURL());
			// Find the response url and get the part of url input here.
			log.info("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					accessToken = twitter
							.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					log.info("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		// persist to the accessToken for future reference.
		storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
		Status status = twitter.updateStatus("Hello This is a test for API.");
		log.info("Successfully updated the status to [" + status.getText()
				+ "].");
		System.exit(0);
	}

	private static void storeAccessToken(long useId, AccessToken accessToken) {
		// store accessToken.getToken()
		// store accessToken.getTokenSecret()
	}
}
