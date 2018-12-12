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

public class BrandTweetCounts {

	private static final String query = "select brand, count(*) from (select regexp_extract(text, 'Adidas|Nike|Puma|Skechers|Reebok', 0) "
			+ "as brand FROM tweets where text is not null) group by brand";
	private static final Logger logger = LogManager.getLogger(BrandTweetCounts.class.getName());

	public static String getBrandTweetCounts() {
		Long startTime = System.currentTimeMillis();
		
		Dataset<Row> sqlDF = InitTweets.getInstance().spark.sql(query);
		List<Row> col = sqlDF.collectAsList();

		Long endTime = System.currentTimeMillis() - startTime;
		logger.debug("BrandTweetCounts :: getBrandTweetCounts :: Exectution time :: " + endTime);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		for (Row cols : col) {
			if (!("").equals(String.valueOf(cols.get(0)))) {
				resultMap.put(String.valueOf(cols.get(0)), String.valueOf(cols.get(1)));
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(resultMap);
		return json;
	}

}
