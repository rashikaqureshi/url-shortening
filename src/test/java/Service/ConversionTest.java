package Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.Conversion;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(MockitoJUnitRunner.class)

public class ConversionTest {
    private Conversion conversion = new Conversion();

    @Test
    public void encode_lessThan62() {
        assertEquals("k", conversion.encode(10));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("bq", conversion.encode(78));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(11, conversion.decode("l"));
    }
}
