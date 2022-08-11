package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceTest {
    private static GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        sut = new GeoServiceImpl();
    }

    @Test
    public void byCoordinatesTest() {
        assertThrows(RuntimeException.class,
                () -> sut.byCoordinates(150, 150));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testLocale(String ip, Location expected) {
        Location result = sut.byIp(ip);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("182.", null),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @AfterEach
    public void finished() {
        sut = null;
    }
}
