package com.himanshukt.url_shortner.service;

import com.himanshukt.url_shortner.dto.CreateShortUrlRequest;
import com.himanshukt.url_shortner.dto.CreateShortUrlResponse;

public interface UrlService {

    public CreateShortUrlResponse shortenUrl(CreateShortUrlRequest request);

    public String getOriginalUrl(String shortCode);
}
