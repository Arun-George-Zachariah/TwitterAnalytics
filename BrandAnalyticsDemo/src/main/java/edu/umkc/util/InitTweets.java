package edu.umkc.util;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import edu.umkc.constants.BrandAnalyticsConstants;

public class InitTweets {

	private static InitTweets instance = null;
	public static SparkSession spark = null;
	
	private InitTweets() {
		
	}
	
	public static InitTweets getInstance() {
		if(instance == null) {
			System.out.println("Initializing tweets");
			instance = new InitTweets();
			spark = SparkSession.builder().appName(BrandAnalyticsConstants.APP_NAME).master(BrandAnalyticsConstants.SPARK_MASTER).getOrCreate();
			Dataset<Row> df = spark.read().json(BrandAnalyticsConstants.IN_FILE);
		    df.createOrReplaceTempView(BrandAnalyticsConstants.TABLE_NAME);
		}
		return instance;
	}
		
}
