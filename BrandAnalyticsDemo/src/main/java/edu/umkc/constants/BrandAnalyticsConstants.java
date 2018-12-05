package edu.umkc.constants;

public class BrandAnalyticsConstants {
	
	public static final String USER_AGENT = "Mozilla/5.0";
	public static final String SENTIMENT_URL = "http://text-processing.com/api/sentiment/";
	public static final String APP_NAME = "Brand_Analytics";
	public static final String SPARK_MASTER = "spark://134.193.128.69:8800";
	public static final String IN_FILE = "hdfs://134.193.128.69:9000/Phase_2/Tweets.json";
	public static final String TABLE_NAME = "tweets";
	
	public static final String query1 = "select brand, count(*) from (select regexp_extract(text, 'Adidas|Nike|Puma|Skechers|Reebok', 0) as brand FROM tweets where text is not null) group by brand";
	public static final String query2 = "select place.country, count(*) FROM tweets where place is not null group by country";
	public static final String query3 = "select user.screen_name, id from tweets order by retweeted_status.retweet_count desc";
	public static final String query4 = "select user.created_at, count(*) from tweets group by user.created_at";
	public static final String query5 = "select user.name,geo.coordinates from tweets where user.verified='true' and geo.coordinates is not  null";
	public static final String query6 = "select day, count(*) from (select substring(user.created_at,1,3) as day from tweets) group by day";
	public static final String query7 = "select user.lang, count(*) as lang_count from tweets group by user.lang order by lang_count desc";
	public static final String query8 = "select possibly_sensitive, count(*) from tweets where possibly_sensitive = 'true' or possibly_sensitive='false' group by possibly_sensitive";
	//To understand the total reach of twitter.
	public static final String query9 = "select sum(user.followers_count), sum(user.friends_count) from tweets";
	//To perform sentiment analysis on the tweets with the most followers.
	public static final String query10 = "select text, user.followers_count from tweets order by user.followers_count desc";
	
	//Total reach 
}
