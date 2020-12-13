package repo;

import lombok.var;
import models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByLongUrlAndClientId(String longUrl);
}
