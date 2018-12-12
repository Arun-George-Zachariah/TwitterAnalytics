package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;

public class FriendsCount {
	
	private static final String query = "select user.screen_name, user.friends_count as c from tweets order by c desc";
	private static final Logger logger = LogManager.getLogger(FriendsCount.class.getName());
	
	public static String getFriendsCount() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();
	    
	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("FriendsCount :: getFriendsCount :: Exectution time :: " + endTime);
		
	    Map<String, String> resultMap = new HashMap<String, String>();
	    int i=1;
	    for(Row cols : col) {
	    	resultMap.put(String.valueOf(cols.get(0)), String.valueOf(cols.get(1)));
	    	i+=1;
	    	if(i == 10) {
	    		break;
	    	}
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
