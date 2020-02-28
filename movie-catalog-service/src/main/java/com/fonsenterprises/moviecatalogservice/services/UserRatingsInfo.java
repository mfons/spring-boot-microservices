package com.fonsenterprises.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fonsenterprises.moviecatalogservice.models.Rating;
import com.fonsenterprises.moviecatalogservice.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingsInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRatings")
	public UserRatings getUserRatings(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRatings.class);
	}
	
	public UserRatings getFallbackUserRatings(String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setUserId(userId);
		userRatings.setRatings(Arrays.asList(new Rating("0", 0)));
		return userRatings;
	}

}
