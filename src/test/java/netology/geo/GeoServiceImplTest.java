package netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @Test
    public void test_byIp() {
        GeoServiceImpl geoServiceimpl = new GeoServiceImpl();
        String ip = "172.0.32.11";
        Country expectedResult = Country.RUSSIA;

        Location location = geoServiceimpl.byIp(ip);
        Country result = location.getCountry();

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test_byCoordinates() {

        GeoServiceImpl geoService = new GeoServiceImpl();
        double latitude = 33.3;
        double longitude = 77.7;

        Assertions.assertThrowsExactly(RuntimeException.class,
                () -> geoService.byCoordinates(latitude, longitude));
    }
}
