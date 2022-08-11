package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceTest {
    private static LocalizationServiceImpl sut;

    @BeforeEach
    public void init() {
        sut = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testLocale(Country country, String result) {
        assertEquals(sut.locale(country), result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.USA, "Welcome"), Arguments.of(Country.BRAZIL, "Welcome"), Arguments.of(Country.RUSSIA, "Добро пожаловать"));
    }

    @AfterEach
    public void finished() {
        sut = null;
    }
}
