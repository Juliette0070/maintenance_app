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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Rental rental : rentals) {
      frequentRenterPoints = rental.getFrequentRenterPoints(frequentRenterPoints);
      // show figures for this rental
      result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
      totalAmount += rental.getCharge();
    }
    // add footer lines
    result += "Amount owed is" + String.valueOf(totalAmount) + "\n";
    result += "You earned  " + String.valueOf(frequentRenterPoints) + " frequent renter points";
    return result;
  }
}
