package app;

public class Rental {
  private Movie movie;
  private int daysRented;
  
  public Rental(Movie movie, int daysRented){
    this.movie = movie;
    this.daysRented = daysRented;
  }
  
  public int getDaysRented() {
    return daysRented;
  }
  
  public Movie getMovie(){
    return movie;
  }

  public double getCharge() {
    double result = 0;
    // determine amoints for aRental line
    switch (getMovie().getPriceCode()) {
      case Movie.REGULAR:
        result += 2;
        if (getDaysRented() > 2)
          result += (getDaysRented() - 2) * 1.5;
        break;
      case Movie.NEW_RELEASE:
        result = getDaysRented() * 3;
        break;
      case Movie.CHILDRENS:
        result += 1.5;
        if (getDaysRented() > 3)
          result += (getDaysRented() - 3) * 1.5;
        break;
    }
    return result;
  }

  public int getFrequentRenterPoints(int frequentRenterPoints) {
    // add frequent renter points
    frequentRenterPoints++;
    // add bonus for a two day new release rental
    if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
      frequentRenterPoints++;
    return frequentRenterPoints;
  }
}