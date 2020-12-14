package com.example.url.Repo;

import com.example.url.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByLongUrlAndClientId(String longUrl,String clientId);
}
