package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;
import edu.umkc.util.TweetUtil;

public class UserTweetDates {
	
	private static final String query = "select user.created_at, count(*) from tweets group by user.created_at";
	
	public static String getUserTweetDates() {
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
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
