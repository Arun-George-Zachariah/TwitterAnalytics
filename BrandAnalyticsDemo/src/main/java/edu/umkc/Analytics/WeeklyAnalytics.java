package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;

public class WeeklyAnalytics {
	
	private static final String query = "select day, count(*) from (SELECT substring(user.created_at,1,3) as day from tweets) group by day";
	
	public static String getWeeklyAnalytics() {
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
	    Map<String, String> resultMap = new HashMap<String, String>();
	    for(Row cols : col) {
	    	System.out.println("Arungeorge :: " + String.valueOf(cols.get(0)));
	    	if(!("null".equals(String.valueOf(cols.get(0))))) {
	    		resultMap.put(String.valueOf(cols.get(0)), String.valueOf(cols.get(1)));
	    	}
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
