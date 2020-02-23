package com.fonsenterprises.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fonsenterprises.moviecatalogservice.models.CatalogItem;
import com.fonsenterprises.moviecatalogservice.models.Movie;
import com.fonsenterprises.moviecatalogservice.models.Rating;
import com.fonsenterprises.moviecatalogservice.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){				
		
		UserRatings userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRatings.class);
		
		return userRatings.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			
			/*
			Movie movie = webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/movies/" + rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();
			*/
			
			return new CatalogItem(movie.getName(), "Test", rating.getRating());	
		})
			.collect(Collectors.toList());
		
		// For each movie ID, call movie info service and get details
		
		// Put them all together
		
	}
}
