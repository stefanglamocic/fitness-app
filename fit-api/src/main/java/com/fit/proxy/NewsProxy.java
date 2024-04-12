package com.fit.proxy;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NewsProxy {
	private static final String newsUrl = "https://feeds.feedburner.com/AceFitFacts";
	
	@GetMapping(path = "/news", produces = { MediaType.TEXT_XML_VALUE })
	public ResponseEntity<String> getNews() {
		RestClient restclient = RestClient.builder()
				.baseUrl(newsUrl)
				.build();
		
		return restclient.get().retrieve().toEntity(String.class);
	}
	
}
