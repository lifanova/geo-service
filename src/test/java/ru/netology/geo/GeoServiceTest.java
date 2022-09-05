package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {
    @Test
    @DisplayName("1. Тест: byIp ru")
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
    @DisplayName("2. Тест: byIp eng")
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
}
