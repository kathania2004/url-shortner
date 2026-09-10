package com.himanshukt.url_shortner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateShortUrlRequest {

    @NotBlank(message = "url cannot be blank")
    @Pattern(
            regexp = "^(https?://).+",
            message = "url must start with http:// or https://"
    )
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
