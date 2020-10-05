package cs3500.pyramidsolitaire.model.hw02;

/**
 * Represents all possible values for a standard deck of cards.
 */
public enum Value {
  Ace("A"), Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"), Eight("8"),
  Nine("9"), Ten("10"), Jack("J"), Queen("Q"), King("K");

  private final String value;

  Value(String value) {
    this.value = value;
  }

  /**
   * Returns the value of the card in the form of an integer.
   *
   * @return the value of the card in the form of an integer
   */
  public int getValue() {
    if (!this.value.equals("A") && !this.value.equals("J")
            && !this.value.equals("Q") && !this.value.equals("K")) {
      return Integer.parseInt(this.value);
    } else if (this.value.equals("A")) {
      return 1;
    } else if (this.value.equals("J")) {
      return 11;
    } else if (this.value.equals("Q")) {
      return 12;
    } else {
      return 13;
    }
  }

}
