package edu.umkc.Impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;

import edu.umkc.Bean.HashTagsBean;
import edu.umkc.Bean.TwitterBean;
import edu.umkc.Bean.URLBean;


public class DataExtractor {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("/home/koushik/CS5540/Project/Porject-20180915T214502Z-001/Porject/TwitterInput.txt")));
				BufferedWriter hashTagWriter = new BufferedWriter(new FileWriter(new File("/home/koushik/CS5540/Project/Porject-20180915T214502Z-001/Porject/TwitterHashTag.txt")));
				BufferedWriter urlWriter = new BufferedWriter(new FileWriter(new File("/home/koushik/CS5540/Project/Porject-20180915T214502Z-001/Porject/TwitterURL.txt")))) {
			String line = null;
			while((line = br.readLine()) != null) {
				TwitterBean twitterData = new Gson().fromJson(line, TwitterBean.class);
				if(twitterData != null && twitterData.getEntities() != null) {
					if(twitterData.getEntities().getHashtags() != null) {
						for(HashTagsBean hashTag : twitterData.getEntities().getHashtags()) {
							hashTagWriter.write(hashTag.getText() + "\n");
							hashTagWriter.flush();
						}
					}
					
					if(twitterData.getEntities().getUrls() != null) {
						for(URLBean urls : twitterData.getEntities().getUrls()) {
							urlWriter.write(urls.getUrl() + "\n");
							urlWriter.flush();
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
