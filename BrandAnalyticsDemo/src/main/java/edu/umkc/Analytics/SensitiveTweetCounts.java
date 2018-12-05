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


public class SensitiveTweetCounts {
	
	private static final String query = "select possibly_sensitive, count(*) from tweets where possibly_sensitive = 'true' or possibly_sensitive='false' group by possibly_sensitive";
	
	public static String getSensitiveTweetCount() {
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
	    
	    List<Row> col = sqlDF.collectAsList();
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
