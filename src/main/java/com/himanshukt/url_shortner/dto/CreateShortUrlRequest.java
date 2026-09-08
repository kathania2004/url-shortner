package com.himanshukt.url_shortner.dto;

public class CreateShortUrlRequest {

    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
