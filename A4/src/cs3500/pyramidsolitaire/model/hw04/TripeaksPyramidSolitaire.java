package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardState;
import cs3500.pyramidsolitaire.model.hw02.GameState;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;

/**
 * A version of BasicPyramidSolitaire that has 3 combined pyramids as opposed to just one.
 */
public class TripeaksPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Instantiates a game of TripeaksPyramidSolitaire.
   */
  public TripeaksPyramidSolitaire() {
    super(GameState.NotStarted, new ArrayList<>(),
            new LinkedList<>(), 0, 0, new Card[0]);
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = this.makeDeck();
    List<Card> deckCopy = this.makeDeck();
    deck.addAll(deckCopy);
    return deck;
  }

  @Override
  public int getNumRows() {
    if (this.gameState == GameState.NotStarted) {
      return -1;
    }

    boolean validRow;
    int counter;
    int rows = 0;
    for (int i = 0; i < this.maxRows; i++) {
      int max = this.getRowWidth(i);
      validRow = false;
      counter = 0;
      while (!validRow && counter < max) {
        if (this.pyramid.get(i).get(counter) != null) {
          validRow = true;
        }
        counter++;
      }
      if (validRow) {
        rows++;
      }
    }
    return rows;
  }

  @Override
  public int getRowWidth(int row) {
    this.gameNotStartedError();

    if (row >= this.maxRows || row < 0) {
      throw new IllegalArgumentException("This is an invalid row.");
    }

    List<Card> r = this.pyramid.get(row);
    return r.size();
  }

  @Override
  boolean invalidDeck(List<Card> deck) {
    if (deck == null || deck.size() != 104) {
      return true;
    } else {
      List<Card> temp = new ArrayList<>();
      temp.addAll(deck);
      // checks each type exists & removes 1/2 the deck
      for (Symbol s : Symbol.values()) {
        for (Value v : Value.values()) {
          Card c = new Card(s, v);
          if (!deck.contains(c)) {
            return true;
          } else {
            temp.remove(c);
          }
        }
      }

      // removes sec 1/2 of deck
      for (Symbol s : Symbol.values()) {
        for (Value v : Value.values()) {
          Card c = new Card(s, v);
          if (!deck.contains(c)) {
            return true;
          } else {
            temp.remove(c);
          }
        }
      }
    }

    return false;
  }

  @Override
  boolean invalidRowsAndDraw(int numRows, int numDraw) {
    if (numDraw < 0 || numRows <= 0 || numRows > 9) {
      return true;
    }
    int total = 0;
    int half = (numRows / 2);
    for (int i = 0; i < half; i++) {
      total += ((i + 1) * 3);
    }
    for (int i = half; i < numRows; i++) {
      // todo
      int distFromHalf = (i + 1) - half;
      total += (half * 3) + distFromHalf;
    }
    return total + numDraw > 104;
  }

  @Override
  boolean invalidCoordinates(int row, int card) {
    int half = (this.maxRows / 2);
    int distFromHalf = row - half;
    return row >= this.maxRows || card < 0 || card > (half * 3) + distFromHalf || row < 0;
  }

  @Override
  void updateAndExpose(int row, int card) {

    // works the same below halfway point for row
    if (isBottomHalf(row)) {

      this.pyramid.get(row).set(card, null);
      this.checkLeftAbove(row, card);
      this.checkRightAbove(row, card);

    } else {
      // above halfway point for row
      if (isRightEdge(row, card)) {
        this.checkLeftAbove(row, card);
      } else if (isLeftEdge(row, card)) {
        this.checkRightAbove(row, card);
      } else {
        this.checkLeftAbove(row, card);
        this.checkRightAbove(row, card);
      }

    }


  }

  @Override
  void sortIntoPyramid() {
    this.pyramid.clear();
    int half = (this.maxRows / 2);
    for (int r = 0; r < this.maxRows; r++) {
      ArrayList<Card> row = new ArrayList<>();
      if (isBottomHalf(r)) {
        int distFromHalf = r - half;

        for (int i = 0; i <= (half * 3) + distFromHalf; i++) {
          Card c = this.stock.pop();
          if (r == this.maxRows - 1) {
            c.cardState = CardState.Exposed;
          } else {
            c.cardState = CardState.NotExposed;
          }
          row.add(c);
        }
      } else {
        for (int i = 0; i < (3 * (r + 1)); i++) {
          Card c = this.stock.pop();
          if (r == this.maxRows - 1) {
            c.cardState = CardState.Exposed;
          } else {
            c.cardState = CardState.NotExposed;
          }
          row.add(c);
          if (i != (3 * (r + 1)) - 1 && (i + 1) % (r + 1) == 0) {
            int numBtw = half - (r + 1);
            for (int n = 0; n < numBtw; n++) {
              row.add(null);
            }
          }
        }
      }

      this.pyramid.add(row);
    }
  }

  void checkLeftAbove(int row, int card) {
    // check left above aka (row - 1, card - 1)
    // by checking if card to c's left (row, card - 1) is removed
    // edge case: c is left-most card
    if (!invalidCoordinates(row - 1, card - 1)) {
      // if left-above is valid, then left is valid
      Card update = this.getCardAt(row - 1, card - 1);
      if (this.pyramid.get(row).get(card - 1) == null) {
        update.cardState = CardState.Exposed;
      }
    }
  }

  void checkRightAbove(int row, int card) {
    // check right above aka (row - 1, card)
    // by checking if card to c's right (row, card + 1) is removed
    // edge case: c is right-most card
    if (!invalidCoordinates(row - 1, card)) {
      // if right-above is valid, then right is valid
      Card update = this.getCardAt(row - 1, card);
      if (this.pyramid.get(row).get(card + 1) == null) {
        update.cardState = CardState.Exposed;
      }
    }
  }

  boolean isRightEdge(int row, int card) {
    int half = (this.maxRows / 2);
    int distFromHalf = row - half;

    if (isBottomHalf(row)) {
      // todo
      if (card == (half * 3) + distFromHalf) {
        return true;
      } else {
        if ((card + 1) % (row + 1) == 0 || (card + 1) == ((row + 1) * 3)) {
          return true;
        }
      }
    }
    return false;
  }

  boolean isLeftEdge(int row, int card) {
    if (isBottomHalf(row)) {
      if (card == 0) {
        return true;
      } else {
        if ((card + 1) % (row + 1) == 1 || card == 0) {
          return true;
        }
      }
    }
    return false;
  }

  boolean isBottomHalf(int row) {
    int half = (this.maxRows / 2);
    return row >= half;
  }



}
