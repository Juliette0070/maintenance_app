package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {

    @Test
    void testStatementRegularMovie() {
        Customer customer = new Customer("Juliette");
        Movie regularMovie = new Movie("Titanic", Movie.REGULAR);
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Juliette\n" +
                "\tTitanic\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementNewReleaseMovie() {
        Customer customer = new Customer("Alice");
        Movie newRelease = new Movie("Avengers", Movie.NEW_RELEASE);
        Rental rental = new Rental(newRelease, 2);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Alice\n" +
                "\tAvengers\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementChildrenMovie() {
        Customer customer = new Customer("Bob");
        Movie childrenMovie = new Movie("Frozen", Movie.CHILDRENS);
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Bob\n" +
                "\tFrozen\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementMultipleMovies() {
        Customer customer = new Customer("Charlie");
        customer.addRental(new Rental(new Movie("Interstellar", Movie.REGULAR), 5));
        customer.addRental(new Rental(new Movie("Inception", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 2));

        String expectedStatement =
                "Rental Record for Charlie\n" +
                "\tInterstellar\t6.5\n" +
                "\tInception\t3.0\n" +
                "\tToy Story\t1.5\n" +
                "Amount owed is 11.0\n" +
                "You earned 3 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementNoMovies() {
        Customer customer = new Customer("David");

        String expectedStatement =
                "Rental Record for David\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementNegativeDaysRented() {
        Customer customer = new Customer("Eve");
        Movie regularMovie = new Movie("Titanic", Movie.REGULAR);
        Rental rental = new Rental(regularMovie, -1);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Eve\n" +
                "\tTitanic\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementRegularMovieOverTwoDaysRented() {
        Customer customer = new Customer("Frank");
        Movie regularMovie = new Movie("Titanic", Movie.REGULAR);
        Rental rental = new Rental(regularMovie, 4);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Frank\n" +
                "\tTitanic\t5.0\n" +
                "Amount owed is 5.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testMovieInvalidPriceCodeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Test Movie", -1);
        });
    }

    @Test
    void testStatementWithValidMovie() {
        Customer customer = new Customer("Grace");
        Movie validMovie = new Movie("Regular Movie", Movie.REGULAR);
        Rental rental = new Rental(validMovie, 3);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Grace\n" +
                "\tRegular Movie\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementZeroDaysRented() {
        Customer customer = new Customer("Hannah");
        Movie regularMovie = new Movie("Titanic", Movie.REGULAR);
        Rental rental = new Rental(regularMovie, 0);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Hannah\n" +
                "\tTitanic\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testStatementNewReleaseOneDay() {
        Customer customer = new Customer("Ian");
        Movie newReleaseMovie = new Movie("Avengers", Movie.NEW_RELEASE);
        Rental rental = new Rental(newReleaseMovie, 1);
        customer.addRental(rental);

        String expectedStatement =
                "Rental Record for Ian\n" +
                "\tAvengers\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    void testInvalidPriceCodeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("Invalid", -1));
    }

    @Test
    void testPriceSubclasses() {
        Price regularPrice = new RegularPrice();
        Price newReleasePrice = new NewReleasePrice();
        Price childrenPrice = new ChildrenPrice();

        assertEquals(2.0, regularPrice.getCharge(1));
        assertEquals(3.0, newReleasePrice.getCharge(1));
        assertEquals(1.5, childrenPrice.getCharge(2));
    }

    @Test
    void testFrequentRenterPoints() {
        Movie regularMovie = new Movie("Titanic", Movie.REGULAR);
        Movie newReleaseMovie = new Movie("Avengers", Movie.NEW_RELEASE);
        Movie childrenMovie = new Movie("Frozen", Movie.CHILDRENS);

        assertEquals(1, regularMovie.getFrequentRenterPoints(1));
        assertEquals(2, newReleaseMovie.getFrequentRenterPoints(2));
        assertEquals(1, childrenMovie.getFrequentRenterPoints(5));
    }
}
