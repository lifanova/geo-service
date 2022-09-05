package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceTest {
    @Test
    @DisplayName("1. Тест: locale ru")
    public void localeTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = Country.RUSSIA;
        String expectedText = "Добро пожаловать";

        String actualText = localizationService.locale(country);

        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("2. Тест: locale eng")
    public void localeEngTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = Country.USA;
        String expectedText = "Welcome";

        String actualText = localizationService.locale(country);

        Assertions.assertEquals(expectedText, actualText);
    }
}
