package netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @Test
    public void test_locale() {

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        Country country = Country.BRAZIL;
        String expectedResult = "Welcome";
        String result = localizationService.locale(country);

        Assertions.assertEquals(expectedResult, result);
    }
}
