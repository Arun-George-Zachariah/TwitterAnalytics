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

public class UserTweetDates {
	
	private static final String query = "select user.created_at, count(*) from tweets group by user.created_at";
	private static final Logger logger = LogManager.getLogger(UserTweetDates.class.getName());
	
	public static String getUserTweetDates() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();

	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("UserTweetDates :: getUserTweetDates :: Exectution time :: " + endTime);
		
	    Map<String, String> resultMap = new HashMap<String, String>();
	    for(Row cols : col) {
	    	if(!("null").equals(String.valueOf(cols.get(0)))) {
		    	resultMap.put(TweetUtil.getDate(String.valueOf(cols.get(0))), String.valueOf(cols.get(1)));
	    	}
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
