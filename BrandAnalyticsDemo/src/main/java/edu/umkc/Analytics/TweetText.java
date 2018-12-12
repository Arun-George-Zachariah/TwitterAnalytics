package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;
import edu.umkc.util.TweetUtil;

public class TweetText {
	
	private static final String query = "select text, user.followers_count from tweets order by user.followers_count desc";
	private static final Logger logger = LogManager.getLogger(TweetText.class.getName());
	
	public static String getText() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();
	    
	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("TweetText :: getText :: Exectution time :: " + endTime);
	    
	    Map<String, String> resultMap = new HashMap<String, String>();
	    for(Row cols : col) {
	    	String tweet = String.valueOf(cols.get(0));
	    	String sentiment = TweetUtil.getInstance().getSentiment(tweet);
	    	resultMap.put(tweet, sentiment);
	    	break;
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
