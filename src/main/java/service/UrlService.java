package service;

import dto.LongUrlRequest;

import models.Url;

import javax.persistence.EntityNotFoundException;

public class UrlService {
    private final repo.UrlRepository urlRepository;
    private final Conversion conversion;

    public UrlService(repo.UrlRepository urlRepository, Conversion conversion) {
        this.urlRepository = urlRepository;
        this.conversion = conversion;
    }

    public String convertToShortUrl(LongUrlRequest request) throws Exception {
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setClientId(request.getClientId());
        Url existingUrl =urlRepository.findByLongUrlAndClientId(request.getLongUrl());
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
