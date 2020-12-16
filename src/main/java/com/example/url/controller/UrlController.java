package com.example.url.controller;

import com.example.url.dto.LongUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.url.service.UrlService;

@RestController
@RequestMapping("/api/v1")
public class UrlController {


    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/convertToShortUrl")
    public String convertToShortUrl(@RequestBody LongUrlRequest request) throws Exception {
        return urlService.convertToShortUrl(request);
    }

    @GetMapping(value = "/get-original-url")
    public ResponseEntity<String> getAndRedirect(@RequestParam String shortUrl) throws Exception {
        String url = urlService.getOriginalUrl(shortUrl);
        return new ResponseEntity<>(url, HttpStatus.TEMPORARY_REDIRECT);
    }

    @GetMapping(value ="/hits")
    public long shortUrlHits(@RequestParam String shortUrl) throws Exception {
        return urlService.getCount(shortUrl);
    }

}


