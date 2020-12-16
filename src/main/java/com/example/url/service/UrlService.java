package com.example.url.service;

import com.example.url.Repo.UrlRepository;
import com.example.url.dto.LongUrlRequest;

import com.example.url.models.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {


    private final UrlRepository urlRepository;
    private final Conversion conversion;

    @Autowired
    public UrlService(UrlRepository urlRepository, Conversion conversion) {
        this.urlRepository = urlRepository;
        this.conversion = conversion;
    }

    public String convertToShortUrl(LongUrlRequest request) throws Exception {
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setClientId(request.getClientId());
        Url existingUrl = urlRepository.findByLongUrlAndClientId(request.getLongUrl(), request.getClientId());
        if (existingUrl != null) {
            return conversion.encode(existingUrl.getId());
        } else {
            Url entity = urlRepository.save(url);
            return conversion.encode(entity.getId());
        }
    }

    public String getOriginalUrl(String shortUrl) throws Exception {
        long id = conversion.decodeShortUrl(shortUrl);
        Optional<Url> entity = urlRepository.findById(id);
        Url url;
        Long count = Long.valueOf(0);
        if (entity.isPresent()) {
            url = entity.get();
            count = url.getCount();
            count = count + 1;
            url.setCount((long) count);
            urlRepository.save(url);
        } else {
            throw new Exception("cannot find short url in database");
        }

        return url.getLongUrl();
    }

    public long getCount(String shortUrl) throws Exception {
        long id = conversion.decodeShortUrl(shortUrl);
        Optional<Url> entity = urlRepository.findById(id);
        Url url;
        if (entity.isPresent()) {
            url = entity.get();
            return url.getCount();
        } else {
            throw new Exception("cannot find short url in database");
        }

    }


}
