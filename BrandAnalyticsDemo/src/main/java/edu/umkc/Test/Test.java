package edu.umkc.Test;

import edu.umkc.constants.BrandAnalyticsConstants;
import edu.umkc.util.InitTweets;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Test {
	public static void main(String[] args) {

		SparkSession spark = SparkSession.builder().appName(BrandAnalyticsConstants.APP_NAME)
				.master(BrandAnalyticsConstants.SPARK_MASTER).getOrCreate();
		Dataset<Row> df = spark.read().json(BrandAnalyticsConstants.IN_FILE);
		df.createOrReplaceTempView(BrandAnalyticsConstants.TABLE_NAME);
		Dataset<Row> sqlDF = spark.sql("SELECT user.name as UserName,user.location as loc,text,created_at,\" +\n"
				+ "      \"CASE WHEN text like '%Adidas%' THEN 'Adidas'\" +\n"
				+ "      \"WHEN text like '%Puma%' THEN 'Puma'\" +\n"
				+ "      \"WHEN text like '%Nike%' THEN 'Nike'\" +\n"
				+ "      \"WHEN text like '%Reebok%' THEN 'Reebok'\" +\n"
				+ "      \"WHEN text LIKE '%Skechers%' THEN 'Skechers'\" +\n"
				+ "      \"END AS brandType from tweets where text is not null");
		sqlDF.show();

	}
}