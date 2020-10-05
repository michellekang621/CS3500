package cs3500.pyramidsolitaire.model.hw02;

/**
 * Represents all possible symbols for a standard deck of cards.
 */
public enum Symbol {
  Club("♣"), Diamond("♦"), Heart("♥"), Spade("♠");

  private final String symbol;

  Symbol(String symbol) {
    this.symbol = symbol;
  }

  public String getValue() {
    return this.symbol;
  }
}
