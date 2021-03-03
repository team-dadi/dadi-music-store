package com.hcl.dadimusicwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dadimusicwebapp.model.Album;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AlbumService {
	@Value("${apiUrl.album}")
	private String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Album> getAll() {
		String getAllUrl = url + "/all";
		log.debug("Sending get all request to: " + getAllUrl);
		List<Album> albumList = restTemplate.getForObject(getAllUrl, List.class);
		return albumList;
	}
	
	public Album getById(int id) {
		String getUrl = url + "/" + id;
		log.debug("Sending get request to: " + getUrl);
		return restTemplate.getForObject(getUrl, Album.class);
	}
	
	public Album add(Album album) {
		log.debug("Sending post request to: " + url);
		return restTemplate.postForObject(url, album, Album.class);
	}
	
	public void update(Album album) {
		log.debug("Sending put request to: " + url);
		restTemplate.put(url, album);
	}
	
	public void delete(int id) {
		String deleteUrl = url + "/" +id;
		log.debug("Sending delete request to: " + deleteUrl);
		restTemplate.delete(deleteUrl);
	}
}
