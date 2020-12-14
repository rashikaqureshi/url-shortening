package com.example.url.service;

import com.example.url.Repo.UrlRepository;
import com.example.url.dto.LongUrlRequest;

import com.example.url.models.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        Url existingUrl =urlRepository.findByLongUrlAndClientId(request.getLongUrl(),request.getClientId());
       if(existingUrl!=null)
       {
           return conversion.encode(existingUrl.getId());
       }
       else {
           Url entity = urlRepository.save(url);

           return conversion.encode(entity.getId());
       }
    }

    public String getOriginalUrl(String shortUrl) {
        long id = conversion.decode(shortUrl);
        Url entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));



        return entity.getLongUrl();
    }
    

}
