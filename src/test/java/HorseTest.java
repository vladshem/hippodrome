import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
class HorseTest {
    @Test
    void whenNameIsNullThenException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
    }

    @Test
    void whenNameIsNullThenExceptionWithText() {
        try {
            Horse horse = new Horse(null, 1);
        } catch (IllegalArgumentException ex) {
            assertEquals("Name cannot be null.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\s"})
    void whenNameContainsTabThenException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\s"})
    void whenNameIsBlankThenExceptionWithText(String name) {
        try {
            Horse horse = new Horse(name, 1);
        } catch (IllegalArgumentException ex) {
            assertEquals("Name cannot be blank.", ex.getMessage());
        }
    }

    @Test
    void whenNegativeSpeedThenException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1));
    }

    @Test
    void whenNegativeSpeedThenExceptionWithText() {
        try {
            Horse horse = new Horse("Test", -1);
        } catch (IllegalArgumentException ex) {
            assertEquals("Speed cannot be negative.", ex.getMessage());
        }
    }

    @Test
    void whenNegativeDistanceThenException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 1, -1));
    }

    @Test
    void whenNegativeDistanceThenExceptionWithText() {
        try {
            Horse horse = new Horse("Test", 1, -1);
        } catch (IllegalArgumentException ex) {
            assertEquals("Distance cannot be negative.", ex.getMessage());
        }
    }

    @Test
    void testGetName() {
        String name = "Test";
        Horse horse = new Horse(name, 1);
        assertEquals(name, horse.getName());
    }

    @Test
    void testGetSpeed() {
        String name = "Test";
        double speed = 5;
        Horse horse = new Horse(name, speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void testGetDistance() {
        String name = "Test";
        double speed = 5;
        double distance = 100;
        Horse horse = new Horse(name, speed, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void testGetDistanceZero() {
        String name = "Test";
        double speed = 5;
        Horse horse = new Horse(name, speed);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void testGetRandomDouble() {
        String name = "Test";
        double speed = 5;
        Horse horse = new Horse(name, speed);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5})
    void testMove(double random) {
        String name = "Test";
        double speed = 5;
        double distance = 10;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(distance + speed * random, horse.getDistance());
        }
    }
}