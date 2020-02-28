package com.fonsenterprises.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fonsenterprises.moviecatalogservice.models.CatalogItem;
import com.fonsenterprises.moviecatalogservice.models.Movie;
import com.fonsenterprises.moviecatalogservice.models.Rating;
import com.fonsenterprises.moviecatalogservice.models.UserRatings;
import com.fonsenterprises.moviecatalogservice.services.MovieInfo;
import com.fonsenterprises.moviecatalogservice.services.UserRatingsInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingsInfo userRatingsInfo;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){				
		
		UserRatings userRatings = userRatingsInfo.getUserRatings(userId);
		
		return userRatings.getRatings().stream()
			.map(rating -> movieInfo.getCatalogItem(rating))
			.collect(Collectors.toList());
		
		// For each movie ID, call movie info service and get details
		
		// Put them all together
		
	}	
}
