package com.himanshukt.url_shortner.controller;


import com.himanshukt.url_shortner.dto.CreateShortUrlRequest;
import com.himanshukt.url_shortner.dto.CreateShortUrlResponse;
import com.himanshukt.url_shortner.service.UrlServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlServiceImpl urlService;

    public UrlController(UrlServiceImpl urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public CreateShortUrlResponse shortenUrl(@RequestBody CreateShortUrlRequest request){
        return urlService.shortenUrl(request);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        String originalUrl = urlService.getOriginalUrl(shortCode);

        return ResponseEntity.
                status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
