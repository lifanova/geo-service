package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {
    @Test
    @DisplayName("1. Тест: русский IP -> русский текст")
    public void onlyRussianTextIsSent() {
        // Russian IP
        String ruIP = "172.123.12.19";
        String expected = "Добро пожаловать";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(ruIP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ruIP);
        // отправляем заголовок с русским адресом
        String locale = messageSender.send(headers);

        Assertions.assertEquals(expected, locale);
    }

    @Test
    @DisplayName("2. Тест: английский IP -> английский текст")
    public void onlyEnglishTextIsSent() {
        // English IP
        String englishIP = "96.44.183.149";
        String expected = "Welcome";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(englishIP))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, englishIP);
        // отправляем заголовок с eng адресом
        String locale = messageSender.send(headers);

        Assertions.assertEquals(expected, locale);
    }

    @Test
    @DisplayName("3. Тест: byIp ru")
    public void byIpTest() {
        //arrange
        GeoService geoService = new GeoServiceImpl();
        String ruIp = "172.123.12.19";

        String expectedCity = "Moscow";
        Country expectedCountry = Country.RUSSIA;

        //act
        Location actualLocation = geoService.byIp(ruIp);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();

        //assert
        Assertions.assertEquals(expectedCity, actualCity);
        Assertions.assertEquals(expectedCountry, actualCountry);
    }

    @Test
    @DisplayName("4. Тест: byIp eng")
    public void byIpEngTest() {
        //arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "96.123.12.19";

        String expectedCity = "New York";
        Country expectedCountry = Country.USA;

        //act
        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();

        //assert
        Assertions.assertEquals(expectedCity, actualCity);
        Assertions.assertEquals(expectedCountry, actualCountry);
    }

    @Test
    @DisplayName("5. Тест: locale ru")
    public void localeTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = Country.RUSSIA;
        String expectedText = "Добро пожаловать";

        String actualText = localizationService.locale(country);

        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("6. Тест: locale eng")
    public void localeEngTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = Country.USA;
        String expectedText = "Welcome";

        String actualText = localizationService.locale(country);

        Assertions.assertEquals(expectedText, actualText);
    }
}
