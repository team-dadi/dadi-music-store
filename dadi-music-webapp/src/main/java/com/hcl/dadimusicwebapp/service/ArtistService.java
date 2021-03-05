package com.hcl.dadimusicwebapp.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.dadimusicwebapp.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dadimusicwebapp.model.Artist;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ArtistService {
	@Value("${apiUrl.artist}")
	private String url;
	@Autowired
	private RestTemplate restTemplate;

	public List<Artist> getAll(){
		String getAllUrl = url+"/all";
		log.debug("Sending get request to: "+ getAllUrl);
		List<Artist> artistList = new ObjectMapper().convertValue(
			restTemplate.getForObject(getAllUrl, List.class),
			new TypeReference<List<Artist>>() { });
		return artistList;
	}
	public Artist getById(int id) {
	    String getUrl = url + "/" + id;
	    log.debug("Sending get request to: " + getUrl);
	    return restTemplate.getForObject(getUrl, Artist.class);
	  }

	  public Artist add(Artist artist) {
	    log.debug("Sending post request to: " + url);
	    return restTemplate.postForObject(url, artist, Artist.class);
	  }

	  public void update(Artist artist) {
	    log.debug("Sending put request to: " + url);
	    restTemplate.put(url, artist);
	  }

	  public void delete(int id) {
	    String deleteUrl = url + "/" + id;
	    log.debug("Sending delete request to: " + deleteUrl);
	    restTemplate.delete(deleteUrl);
	  }
}
