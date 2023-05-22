import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HippodromeTest {

    @Test
    void whenListIsNullThenException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void whenListIsNullThenExceptionWithText() {
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Horses cannot be null.", ex.getMessage());
        }
    }

    @Test
    void whenListIsEmptyThenException() {

        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void whenListIsEmptyThenExceptionWithText() {

        List<Horse> horses = new ArrayList<>();
        try {
            Hippodrome hippodrome = new Hippodrome(horses);
        } catch (IllegalArgumentException ex) {
            assertEquals("Horses cannot be empty.", ex.getMessage());
        }
    }

    @Test
    void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i=0; i< 30; i++) {
            horses.add(new Horse("Horse" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void testMove() {
        Horse horseMock = Mockito.mock(Horse.class);
        List<Horse> horseMockList = new ArrayList<>();
        for (int i=0; i < 50; i++) {
            horseMockList.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horseMockList);

        hippodrome.move();

        Mockito.verify(horseMock, Mockito.times(50)).move();
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i=0; i< 30; i++) {
            horses.add(new Horse("Horse" + i, 1, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(29), hippodrome.getWinner());
    }
}