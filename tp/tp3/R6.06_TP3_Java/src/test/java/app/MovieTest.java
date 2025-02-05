package app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTest {
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Avatar", Movie.REGULAR);
    }

    @Test
    void testGetTitle() {
        assertEquals("Avatar", movie.getTitle(), "Le titre du film devrait être 'Avatar'");
    }

    @Test
    void testGetPriceCode() {
        assertEquals(Movie.REGULAR, movie.getPriceCode(), "Le code tarifaire devrait être REGULAR");
    }

    @Test
    void testSetPriceCode() {
        movie.setPriceCode(Movie.NEW_RELEASE);
        assertEquals(Movie.NEW_RELEASE, movie.getPriceCode(), "Le code tarifaire devrait être mis à jour en NEW_RELEASE");
    }
}