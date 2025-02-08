package app;

public abstract class Price {
    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1; // Valeur par défaut, sauf pour les nouveautés
    }
}
