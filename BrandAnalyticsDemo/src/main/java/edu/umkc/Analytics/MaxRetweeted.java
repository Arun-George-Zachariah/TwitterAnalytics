package edu.umkc.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.google.gson.Gson;

import edu.umkc.util.InitTweets;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;


public class MaxRetweeted {
	
	private static final String query = "select user.screen_name, id from tweets order by retweeted_status.retweet_count desc";
	
	public static String getTopRetweeted() {
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
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
