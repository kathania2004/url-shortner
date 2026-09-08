package com.himanshukt.url_shortner.controller;


import com.himanshukt.url_shortner.dto.CreateShortUrlRequest;
import com.himanshukt.url_shortner.dto.CreateShortUrlResponse;
import com.himanshukt.url_shortner.service.UrlServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private UrlServiceImpl urlService;

    public UrlController(UrlServiceImpl urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public CreateShortUrlResponse shortenUrl(@RequestBody CreateShortUrlRequest request){
        return urlService.shortenUrl(request);

    }
}
