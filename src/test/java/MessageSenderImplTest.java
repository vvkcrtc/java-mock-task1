import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.geo.GeoService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    public void messageSenderTest() {
    String expected  = "Добро пожаловать";
    Location lock = new Location("Moscow", Country.RUSSIA,null,0);

    GeoService geoService = Mockito.mock(GeoService.class);
    Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(lock);

    LocalizationService localizationService =  Mockito.mock(LocalizationService.class);
    Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(expected);


    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<String, String>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
    String value = messageSender.send(headers);

    Assertions.assertEquals(expected, value);

}
/*
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        Assertions.assertTrue(Numbers.isOdd(number));
    }

    @Test
    public void byIpTest() {
        Location expected = new Location("Moscow", Country.RUSSIA,null,0);
        GeoService geoService = new GeoServiceImpl();
        Location value = geoService.byIp("172.");
        Assertions.assertEquals(expected.getCountry(), value.getCountry());

    }

 */
}
