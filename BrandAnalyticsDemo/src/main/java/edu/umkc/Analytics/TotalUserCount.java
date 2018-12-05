package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;

public class TotalUserCount {
	
	private static final String query = "select sum(user.followers_count), sum(user.friends_count) from tweets";
	
	public static String getTotalUsersCount() {
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
	    Map<String, Long> resultMap = new HashMap<String, Long>();
	    for(Row cols : col) {
	    	Long count = Long.parseLong(String.valueOf(cols.get(0))) + Long.parseLong(String.valueOf(cols.get(1)));
	    	resultMap.put("Count", count);
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
