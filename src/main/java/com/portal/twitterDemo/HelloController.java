package com.portal.twitterDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class HelloController {
	
	  public static String api_key = "pb0xPw0qld9Xyu911WtMVxfMN";
	  public static String api_secret = "wQZ9VMaq9iPk5kw16ot3PVwV9PO2mtD9rKwg9NUZEfvdvg2YPx";
	  public static String access_token = "1014646536-cfDB70m5I6jBO3MdsimSqyyKxG50CQhFBd0HNQb";
	  public static String access_token_secret = "LE9QewOMD4LyoykXdCjYbAx36hQKQC30zYE613jt2sq0X";

	
	@GetMapping("/")
	public String showHello() {
		return "hello lipsa";
		
	}
	
	@GetMapping("/showTwitter")
	public List<String> showTwitter() {
		
		 ConfigurationBuilder cb = new ConfigurationBuilder();
		    cb.setOAuthConsumerKey(api_key);
		    cb.setOAuthConsumerSecret(api_secret);
		    cb.setOAuthAccessToken(access_token);
		    cb.setOAuthAccessTokenSecret(access_token_secret);

		    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		    
		    
		    AccessToken accessToken = new AccessToken(access_token, access_token_secret);
           // twitter.setOAuthConsumer(api_key, api_secret);
            twitter.setOAuthAccessToken(accessToken);
            List<Status> tweets=null;
            try {
                Query query = new Query("IBM");
                QueryResult result;
                result = twitter.search(query);
                return result.getTweets().stream()
                	      .map(item -> item.getText())
                	      .collect(Collectors.toList());
               /*  tweets = result.getTweets();
                 
                 System.out.println("size@@@@@@@@@@@@@@@2" + tweets.size());
                for (Status tweet : tweets) {
                	
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }*/
            }
            catch (TwitterException te) {
                te.printStackTrace();
                System.out.println("Failed to search tweets: " + te.getMessage());
                System.exit(-1);
            }

	
		    
		    return null;
		
	}

}
