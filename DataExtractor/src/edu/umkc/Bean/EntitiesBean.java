package edu.umkc.Bean;

import java.util.List;

public class EntitiesBean {
	private List<HashTagsBean> hashtags ;
	private List<URLBean> urls;

	public List<HashTagsBean> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<HashTagsBean> hashtags) {
		this.hashtags = hashtags;
	}

	public List<URLBean> getUrls() {
		return urls;
	}

	public void setUrls(List<URLBean> urls) {
		this.urls = urls;
	}

}
