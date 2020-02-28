package com.fonsenterprises.moviecatalogservice.models;

import java.util.List;

public class UserRatings {
	

	public UserRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public UserRatings() {
	}
	
	private List<Rating> ratings;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}
