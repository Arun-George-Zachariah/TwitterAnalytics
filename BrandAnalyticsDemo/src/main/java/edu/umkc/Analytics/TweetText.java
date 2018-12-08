package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;
import edu.umkc.util.TweetUtil;

public class TweetText {
	
	private static final String query = "select text, user.followers_count from tweets order by user.followers_count desc";
	
	public static String getText() {
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
	    Map<String, String> resultMap = new HashMap<String, String>();
	    int i =1;
	    for(Row cols : col) {
	    	if(i >= 10) {
	    		break;
	    	}
	    	String tweet = String.valueOf(cols.get(0));
	    	String sentiment = TweetUtil.getInstance().getSentiment(tweet);
	    	resultMap.put(tweet, sentiment);
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
