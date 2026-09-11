package com.himanshukt.url_shortner.service;

import com.himanshukt.url_shortner.dto.CreateShortUrlRequest;
import com.himanshukt.url_shortner.dto.CreateShortUrlResponse;
import com.himanshukt.url_shortner.entity.UrlMapping;
import com.himanshukt.url_shortner.exception.UrlNotFoundException;
import com.himanshukt.url_shortner.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UrlServiceImpl implements UrlService{

    private final UrlMappingRepository urlMappingRepository;

    public UrlServiceImpl(UrlMappingRepository urlMappingRepository){
        this.urlMappingRepository = urlMappingRepository;
    }

    private static final String characters =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    @Override
    public CreateShortUrlResponse shortenUrl(CreateShortUrlRequest request){


        String shortCode;
        do{
            shortCode = generateShortCode();
        }while(urlMappingRepository.findByshortCode(shortCode).isPresent());


        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(request.getOriginalUrl());
        urlMapping.setShortCode(shortCode);

        urlMappingRepository.save(urlMapping);

        String shortUrl = "http://localhost:8080/" +shortCode;

        CreateShortUrlResponse response = new CreateShortUrlResponse();
        response.setOriginalUrl(request.getOriginalUrl());
        response.setShortUrl(shortUrl);

        return response;

    }

    private String generateShortCode(){
        StringBuilder shortcode = new StringBuilder();

        for(int i = 0; i < 5; i++){
            int index = (int)(Math.random() * characters.length());
            shortcode.append(characters.charAt(index));
        }
        return shortcode.toString();
    }

    @Override
    public String getOriginalUrl(String shortCode){

        UrlMapping urlMapping  = urlMappingRepository.findByshortCode(shortCode).orElseThrow(() ->
                new UrlNotFoundException("Short url not found"));

        if(urlMapping.getExpiresAt() != null && urlMapping.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new UrlNotFoundException("short url has expired");
        }

        //increment click count
        urlMapping.setClickCount(urlMapping.getClickCount() + 1);

        urlMappingRepository.save(urlMapping);

        return urlMapping.getOriginalUrl();
    }
}
