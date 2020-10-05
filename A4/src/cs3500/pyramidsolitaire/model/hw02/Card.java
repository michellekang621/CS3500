package cs3500.pyramidsolitaire.model.hw02;

/**
 * Represents a Card in a standard deck for BasicPyramidSolitaire.
 */
public class Card {
  public Symbol sym;
  public Value val;
  public CardState cardState;

  /**
   * The initializing constructor.
   *
   * @param sym card's symbol
   * @param val card's value
   */
  public Card(Symbol sym, Value val) {
    this.sym = sym;
    this.val = val;
    this.cardState = CardState.Other;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Card)) {
      return false;
    }

    return this.val.getValue() == ((Card) that).val.getValue()
            && this.sym.getValue().equals(((Card) that).sym.getValue());
  }

  @Override
  public int hashCode() {
    // determine prime # to represent individual symbols
    int prime = 0;
    switch (this.sym.getValue()) {
      case "♣":
        prime = 5;
        break;
      case "♦":
        prime = 7;
        break;
      case "♥":
        prime = 17;
        break;
      case "♠":
        prime = 31;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + this.sym.getValue());
    }

    return Long.hashCode(prime * this.val.getValue());
  }

  @Override
  public String toString() {
    if (this.val.getValue() != 1 && this.val.getValue() != 11
            && this.val.getValue() != 12 && this.val.getValue() != 13) {
      return this.val.getValue() + this.sym.getValue();
    } else if (this.val.getValue() == 1) {
      return "A" + this.sym.getValue();
    } else if (this.val.getValue() == 11) {
      return "J" + this.sym.getValue();
    } else if (this.val.getValue() == 12) {
      return "Q" + this.sym.getValue();
    } else {
      return "K" + this.sym.getValue();
    }
  }

  // returns the cardState of this card.
  public CardState returnState() {
    return this.cardState;
  }

  // returns the numerical value of this card according to the game if PyramidSolitaire.
  public int getValue() {
    return this.val.getValue();
  }

}
