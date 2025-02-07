package app;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private String name;
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental arg) {
    // rentals.addElement(arg);
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    String result = "Rental Record for " + getName() + "\n";
    for (Rental rental : rentals) {
      result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
    }
    // add footer lines
    result += "Amount owed is " + getTotalCharge() + "\n";
    result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
    return result;
  }

  private double getTotalCharge() {
    double totalAmount = 0;
    for (Rental rental : rentals) {
      totalAmount += rental.getCharge();
    }
    return totalAmount;
  }

  private int getTotalFrequentRenterPoints() {
    int frequentRenterPoints = 0;
    for (Rental rental : rentals) {
      frequentRenterPoints = rental.getFrequentRenterPoints(frequentRenterPoints);
    }
    return frequentRenterPoints;
  }
}
