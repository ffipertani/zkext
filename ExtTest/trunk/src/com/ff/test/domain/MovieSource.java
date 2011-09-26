package com.ff.test.domain;

import java.util.ArrayList;
import java.util.List;

public class MovieSource {
	private String id;
	private String title;
	private String imdbKey;
	private String originalTitle;
	private String description;
	private String story;
	private int year;
	private String imageUrl;
	private String production;
	private String fullTitle;
	private Boolean subtitled;
	private String pageUrl;
	private int duration;
	
	private String category;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullTitle() {
		return fullTitle;
	}
	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}
	public Boolean getSubtitled() {
		return subtitled;
	}
	public void setSubtitled(Boolean subtitled) {
		this.subtitled = subtitled;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getOriginalTitle() {
		return originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImdbKey() {
		return imdbKey;
	}
	public void setImdbKey(String imdbKey) {
		this.imdbKey = imdbKey;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}
