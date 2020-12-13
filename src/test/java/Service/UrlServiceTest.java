package Service;

import dto.LongUrlRequest;
import models.Url;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.Conversion;
import service.UrlService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    @Mock
    repo.UrlRepository mockUrlRepository;

    @Mock
    Conversion mockConversion;

    @InjectMocks
    UrlService urlService;

    @Test
    public void convertToShortUrlTest() throws Exception {
        Url url = new Url();
        url.setLongUrl("https://upsconline.nic.in/mainmenu2.phphttps://upsconlinenic.in/mainmenu2.php");
        url.setClientId("5");

        when(mockUrlRepository.save(any(Url.class))).thenReturn(url);
        when(mockConversion.encode(url.getId())).thenReturn("f");

        LongUrlRequest urlRequest = new LongUrlRequest();
        urlRequest.setLongUrl("https://upsconline.nic.in/mainmenu2.phphttps://upsconlinenic.in/mainmenu2.php");

        assertEquals("f", urlService.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockConversion.decode("h")).thenReturn((long) 7);

        Url url = new Url();
        url.setLongUrl("https://upsconline.nic.in/mainmenu2.phphttps://upsconlinenic.in/mainmenu2.php");
        url.setClientId("7");

        when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://upsconline.nic.in/mainmenu2.phphttps://upsconlinenic.in/mainmenu2.php", urlService.getOriginalUrl("h"));

    }
}
