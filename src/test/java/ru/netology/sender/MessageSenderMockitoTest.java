package ru.netology.sender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderMockitoTest {
    MessageSenderImpl sut;

    @BeforeEach
    public void init() {
        LocalizationService local = Mockito.mock(LocalizationService.class);
        Mockito.when(local.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(local.locale(Country.USA)).thenReturn("Welcome");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null, 0));

        sut = new MessageSenderImpl(geoService, local);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void sendMockitoTest(Map<String, String> headers, String expected) {
        String resultMessageSender = sut.send(headers);
        Assertions.assertEquals(resultMessageSender, expected);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(Map.of("x-real-ip", "172."), "Добро пожаловать"),
                Arguments.of(Map.of("x-real-ip", "96."), "Welcome")
        );
    }

    @AfterEach
    public void finalized() {
        sut = null;
    }

}
