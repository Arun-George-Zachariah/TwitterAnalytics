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

public class SensitiveTweetCounts {
	
	private static final String query = "select possibly_sensitive, count(*) from tweets where possibly_sensitive = 'true' or "
			+ "possibly_sensitive='false' group by possibly_sensitive";
	private static final Logger logger = LogManager.getLogger(SensitiveTweetCounts.class.getName());
	
	public static String getSensitiveTweetCount() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();

	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("BrandTweetCounts :: getSensitiveTweetCount :: Exectution time :: " + endTime);
		
	    Map<String, String> resultMap = new HashMap<String, String>();
	    for(Row cols : col) {
	    	if("true".equals(String.valueOf(cols.get(0)))) {
			    resultMap.put("Sensitive", String.valueOf(cols.get(1)));
	    	} else {
			    resultMap.put("Non Sensitive", String.valueOf(cols.get(1)));
	    	}
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
