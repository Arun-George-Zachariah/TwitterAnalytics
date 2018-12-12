package edu.umkc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.json.JSONObject;

import edu.umkc.constants.BrandAnalyticsConstants;

public class TweetUtil {
	private static TweetUtil instance = null;
	
	private TweetUtil() {
		
	}
	
	public static TweetUtil getInstance() {
		if(instance == null) {
			instance = new TweetUtil();
		}
		return instance;
	}
	
	public static String getDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		LocalDate dt = LocalDate.parse(date, formatter);
		return dt.toString();
	}
	
	public static String getSentiment(String input) {
		String retVal = null;
		try {
			URL obj = new URL(BrandAnalyticsConstants.SENTIMENT_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", BrandAnalyticsConstants.USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "text=" + input;
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			JSONObject jsonObj = new JSONObject(response.toString());
			retVal = (String) jsonObj.get("label");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The sentiment returned :: " + retVal);
		return retVal;
	}
}
