package app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalTest {
    private Movie movie;
    private Rental rental;

    @BeforeEach
    void setUp() {
        movie = new Movie("Inception", 1); // Supposons que 1 représente un type de film
        rental = new Rental(movie, 5);
    }

    @Test
    void testGetDaysRented() {
        assertEquals(5, rental.getDaysRented(), "Le nombre de jours loués devrait être 5");
    }

    @Test
    void testGetMovie() {
        assertEquals(movie, rental.getMovie(), "Le film récupéré devrait être celui initialisé");
    }

    @Test
    void testGetCharge() {
        assertEquals(15.0, rental.getCharge(), "Le montant de la location devrait être de 15.0");
    }
}
