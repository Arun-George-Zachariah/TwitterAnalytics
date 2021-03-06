package edu.umkc.Analytics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;

public class LanguageDistribution {
	
	private static final String query = "select user.lang, count(*) as lang_count from tweets group by user.lang order by lang_count desc";
	private static final Logger logger = LogManager.getLogger(LanguageDistribution.class.getName());
	
	public static String getLangDist() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    List<Row> col = sqlDF.collectAsList();
	    
	    Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("LanguageDistribution :: getLangDist :: Exectution time :: " + endTime);
		
	    Map<String, String> resultMap = new LinkedHashMap<String, String>();
	    int i=1;
	    for(Row cols : col) {
	    	resultMap.put(String.valueOf(cols.get(0)), String.valueOf(cols.get(1)));
	    	if(i == 10) {
	    		break;
	    	}
	    	i++;
	    }
	    
	    Gson gson = new Gson(); 
	    String json = gson.toJson(resultMap);
	    return json;
	}
	
}
