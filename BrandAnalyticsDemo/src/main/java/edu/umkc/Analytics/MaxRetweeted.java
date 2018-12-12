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

public class MaxRetweeted {
	
	private static final String query = "select user.screen_name, id from tweets order by retweeted_status.retweet_count desc";
	private static final Logger logger = LogManager.getLogger(MaxRetweeted.class.getName());
	
	public static String getTopRetweeted() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();
	    
	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("MaxRetweeted :: getTopRetweeted :: Exectution time :: " + endTime);
		
	    Map<String, String> resultMap = new HashMap<String, String>();
	    for(Row cols : col) {
			    resultMap.put(String.valueOf(cols.get(0)), String.valueOf(cols.get(1)));
			    break;
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
