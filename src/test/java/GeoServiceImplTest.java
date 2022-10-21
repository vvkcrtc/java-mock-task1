import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class GeoServiceImplTest {

/*
// Параметризованный тест у меня не получилось сделать

    static Map<String, String> val = new HashMap<String, String>();
    GeoService geoService = new GeoServiceImpl();
    @BeforeAll
    static void init() {
        val.put("127.0.0.1","");
        val.put("172.0.32.11","RUSSIA");
        val.put("96.44.183.149","USA");
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149"})
    public void byIpTest(String ipStr) {
        Location value = geoService.byIp(ipStr);
        Assertions.assertEquals(val.get(ipStr), value.getCountry());

    }


 */

    @Test
    public void byIpTest() {
        Location expected = new Location("Moscow", Country.RUSSIA,null,0);
        GeoService geoService = new GeoServiceImpl();
        Location value = geoService.byIp("172.");
        Assertions.assertEquals(expected.getCountry(), value.getCountry());

    }

    @Test
    public void byCoordinatesThrowTest() {
        GeoService geoService = new GeoServiceImpl();
        RuntimeException throwTest = Assertions.assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(0,0),"Not implemented");

    }


}
