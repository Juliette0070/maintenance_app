package app;

public class Movie {
  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;
  
  private String title;
  private Price price;
  
  public Movie(String title, int priceCode)  {
    this.title = title;
    setPriceCode(priceCode);
  }
  
  public int getPriceCode() {
    if (price instanceof RegularPrice) return REGULAR;
    if (price instanceof NewReleasePrice) return NEW_RELEASE;
    if (price instanceof ChildrenPrice) return CHILDRENS;
    throw new IllegalStateException("Unknown price type");
  }
    
  public void setPriceCode(int priceCode) {
    switch (priceCode) {
        case REGULAR:
            price = new RegularPrice();
            break;
        case NEW_RELEASE:
            price = new NewReleasePrice();
            break;
        case CHILDRENS:
            price = new ChildrenPrice();
            break;
        default:
            throw new IllegalArgumentException("Invalid price code");
    }
  }
  
  public String getTitle() {
    return title;
  }

  public double getCharge(int daysRented) {
    return price.getCharge(daysRented);
  }

  public int getFrequentRenterPoints(int daysRented) {
    return price.getFrequentRenterPoints(daysRented);
  }
}
