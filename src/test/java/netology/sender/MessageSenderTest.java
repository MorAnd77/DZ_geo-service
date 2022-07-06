package netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderTest {

    @ParameterizedTest
    @MethodSource("test_source")
    public void test_send(Location location, String ip, String expected) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);
        Assertions.assertEquals(result, expected);
    }

    private static Stream<Arguments> test_source() {
        Location locationRus = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        String ipRus = "172.0.32.11";
        String expectedRus = "Добро пожаловать";
        Location locationUsa = new Location("New York", Country.USA, " 10th Avenue", 32);
        String ipUsa = "96.44.183.149";
        String expectedUsa = "Welcome";
        return Stream.of(
                Arguments.of(locationRus, ipRus, expectedRus),
                Arguments.of(locationUsa, ipUsa, expectedUsa)
        );
    }
}
