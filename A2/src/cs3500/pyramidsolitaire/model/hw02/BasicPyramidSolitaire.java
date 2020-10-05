package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a basic implementation of the game Pyramid Solitaire.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {

  private GameState gameState;
  private List<List<Card>> pyramid;
  private LinkedList<Card> stock;
  private int maxRows;
  private int maxDraw;
  private Card[] draw;

  /**
   * Constructor for testing the game.
   */
  public BasicPyramidSolitaire() {
    this.gameState = GameState.NotStarted;
    this.pyramid = new ArrayList<>();
    this.stock = new LinkedList<>();
    this.maxRows = 0;
    this.maxDraw = 0;
    this.draw = new Card[1];
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (Symbol sym : Symbol.values()) {
      for (Value val : Value.values()) {
        deck.add(new Card(sym, val));
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    // ILLEGALARGUMENTEXCEPTION
    // deck is null
    if (deck == null) {
      throw new IllegalArgumentException("startGame: Null deck!");
    }

    // deck is invalid if:
    // 1) size of deck is not 52
    if (deck.size() != 52) {
      throw new IllegalArgumentException("startGame: Invalid deck!");
    }

    // 2) doesn't have all the cards a deck should have
    for (Symbol s : Symbol.values()) {
      for (Value v : Value.values()) {
        if (!deck.contains(new Card(s, v))) {
          throw new IllegalArgumentException("invalid layout");
        }
      }
    }

    // another input is invalid
    // numDraw or numRows are negative
    if (numDraw < 0 || numRows <= 0) {
      throw new IllegalArgumentException("startGame: Invalid input!");
    }

    // inputs don't add up to 52
    int total = 0;
    for (int i = 1; i <= numRows; i++) {
      total += i;
    }
    if (total + numDraw > 52) {
      throw new IllegalArgumentException("startGame: Invalid input!");
    }

    // STARTGAME:
    // 1) shuffle or not?
    if (shouldShuffle) {
      Collections.shuffle(deck);
    }

    // 2) change CardStates according to input parameters,
    // while also sorting into corresponding sections/assignments
    // 2A) sort into Pyramid pile
    LinkedList<Card> tempLL = new LinkedList<>(deck);
    for (int r = 0; r < numRows; r++) {
      ArrayList<Card> row = new ArrayList<>();
      for (int i = 0; i <= r; i++) {
        Card c = tempLL.pop();
        if (r == numRows - 1) {
          c.cardState = CardState.Exposed;
        } else {
          c.cardState = CardState.NotExposed;
        }
        row.add(c);
      }
      this.pyramid.add(row);
    }

    // 2B) sort into Draw pile
    if (numDraw > 0) {
      this.draw = new Card[numDraw];
      for (int d = 0; d < numDraw; d++) {
        this.draw[d] = tempLL.pop();
      }
    }


    // 2C) put rest into Stock pile
    this.stock.addAll(tempLL);

    if (numDraw == 0 && !this.possThirteen()) {
      throw new IllegalArgumentException();
    }

    // 3) change GameState to be GameState.Started
    this.gameState = GameState.Started;
    // 4) set maxRows to correspond to input numRows
    this.maxRows = numRows;
    // 5) set numDraw to correspond to max # of exposed draw cards at a time
    this.maxDraw = numDraw;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // move is invalid if:
    // 1) either coordinates aren't valid
    if (!validCoordinates(row1, card1) || !validCoordinates(row2, card2)) {
      throw new IllegalArgumentException("remove(2 cards): Invalid coordinates!");
    }

    Card c1 = this.getCardAt(row1, card1);
    Card c2 = this.getCardAt(row2, card2);

    // 2) either Cards are not in the right state
    if (invalidCardState(c1) || invalidCardState(c2)) {
      throw new IllegalArgumentException("remove(2 cards): Invalid card states!");
    }

    // 3) cards don't add up to 13
    if (c1.val.getValue() + c2.val.getValue() != 13) {
      throw new IllegalArgumentException("remove(2 cards): Invalid sum!");
    }

    // 1) Change CardStates of both Cards to CardState.Removed
    c1.cardState = CardState.Removed;
    c2.cardState = CardState.Removed;

    // 2) change CardState(s) of newly-exposed Card(s) in Pyramid
    // (if there are any) to CardState.Exposed
    this.expose(row1, card1);
    this.expose(row2, card2);

  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // move is invalid if:
    // 1) coordinates aren't valid
    if (!this.validCoordinates(row, card)) {
      throw new IllegalArgumentException("remove(1): Invalid coordinates!");
    }

    Card c = this.getCardAt(row, card);
    // 2) card is not in right state
    if (this.invalidCardState(c)) {
      throw new IllegalArgumentException("remove(1): Invalid cardState!");
    }

    // 3) card's value doesn't equal 13
    if (c.val.getValue() != 13) {
      throw new IllegalArgumentException("remove(1): Invalid sum!");
    }

    // 1) Change CardState to CardState.Removed
    c.cardState = CardState.Removed;

    // 2) change CardState(s) of newly-exposed Card(s)
    // in Pyramid (if there are any) to CardState.Exposed
    this.expose(row, card);

  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // move is invalid if:
    // 1) either "coordinates" are invalid
    if (!this.validCoordinates(row, card) || (drawIndex >= this.maxDraw)
            || (drawIndex < 0) || (this.draw[drawIndex] == null)) {
      throw new IllegalArgumentException("removeUsingDraw: Invalid "
              + "coordinates OR invalid drawIdx!");
    }

    Card pCard = this.getCardAt(row, card);
    Card dCard = this.draw[drawIndex];
    // 2) either card is not in right state
    if (this.invalidCardState(pCard)) {
      throw new IllegalArgumentException("removeUsingDraw: Invalid cardState!");
    }
    // 3) cards don't add up to 13
    if (pCard.val.getValue() + dCard.val.getValue() != 13) {
      throw new IllegalArgumentException("removeUsingDraw: Invalid sum!");
    }

    // 1) Change CardState of pCard to CardState.Removed
    pCard.cardState = CardState.Removed;
    dCard.cardState = CardState.Removed;

    // 3) get next stockCard from stockList (if any left) replace current card with it at drawIndex
    if (this.stock.size() > 0) {
      this.draw[drawIndex] = this.stock.pop();
    } else {
      this.draw[drawIndex] = null;
    }

    // 5) change CardState(s) of newly-exposed Card(s) in Pyramid
    // (if there are any) to CardState.Exposed
    this.expose(row, card);

  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // index is invalid OR no card is present there
    if (drawIndex >= this.maxDraw || drawIndex < 0 || this.draw[drawIndex] == null) {
      throw new IllegalArgumentException("discardDraw: Invalid drawIdx!");
    }

    this.draw[drawIndex].cardState = CardState.Removed;

    // 1) Change CardState of Card to CardState.Removed
    if (this.stock.size() > 0) {
      this.draw[drawIndex] = this.stock.pop();
    } else {
      this.draw[drawIndex] = null;
    }
  }

  @Override
  public int getNumRows() {
    boolean validRow;
    int rows = 0;
    if (this.gameNotStarted()) {
      return -1;
    } else {
      for (int i = 0; i < this.maxRows; i++) {
        validRow = false;
        for (int j = 0; j <= i; j++) {
          // is there at least one "not removed" card in this row
          Card c = this.getCardAt(i, j);
          if (c.cardState != CardState.Removed) {
            validRow = true;
          }
        }
        if (validRow) {
          rows++;
        }
      }
    }
    return rows;
  }

  @Override
  public int getNumDraw() {
    int count = 0;
    if (this.gameNotStarted()) {
      return -1;
    } else {
      for (int i = 0; i < this.maxDraw; i++) {
        if (this.draw[i] != null) {
          count++;
        }
      }
      return count;
    }
  }

  @Override
  public int getRowWidth(int row) {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // row is invalid
    if (row >= this.maxRows) {
      throw new IllegalArgumentException("getRowWidth: Invalid row!");
    }

    // return # of cards dealt into given row
    return row + 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // game is over if there are no more valid moves that can be made
    // possible valid moves include... :
    // 1) two exposed cards from pyramid == 13
    ArrayList<Card> exposed = new ArrayList<>();
    for (int i = 0; i < this.maxRows; i++) {
      List<Card> t = this.pyramid.get(i);
      for (int j = 0; j < t.size(); j++) {
        Card c = this.getCardAt(i, j);
        if (c.cardState == CardState.Exposed) {
          exposed.add(c);
        }
      }
    }

    if (exposed.size() == 0 || this.getScore() == 0) {
      return true;
    }

    for (int i = 0; i < exposed.size(); i++) {
      for (int j = 0; j < exposed.size(); j++) {
        if (i != j) {
          Card iC = exposed.get(i);
          Card jC = exposed.get(j);

          if (iC.val.getValue() + jC.val.getValue() == 13) {
            return false;
          }
        }
      }
    }

    // 2) one exposed card from pyramid == 13
    for (Card card : exposed) {
      if (card.val.getValue() == 13) {
        return false;
      }
    }

    // 3) one exposed card from pyramid + one card from draw == 13
    List<Card> drawTemp = new ArrayList<>(this.getDrawCards());
    for (Card card : exposed) {
      for (Card value : drawTemp) {

        if (card.val.getValue() + value.val.getValue() == 13) {
          return false;
        }
      }
    }

    // 4) there are no more cards to be removed from draw/stock pile
    return this.stock.size() <= 0 && this.getDrawCards().size() <= 0;
  }

  @Override
  public int getScore() throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // return sum of the values of the cards remaining in the pyramid.
    int score = 0;
    for (int r = 0; r < this.maxRows; r++) {
      for (int i = 0; i <= r; i++) {
        Card c = this.pyramid.get(r).get(i);
        if (c.cardState != CardState.Removed) {
          score += c.val.getValue();
        }
      }
    }
    return score;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // ILLEGALARGUMENTEXCEPTION
    // coordinates are invalid
    if (!this.validCoordinates(row, card)) {
      throw new IllegalArgumentException("getCardAt: Invalid coordinates!");
    }

    // return card at specified coordinates
    return this.pyramid.get(row).get(card);
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    // ILLEGALSTATEEXCEPTION
    if (this.gameNotStarted()) {
      throw new IllegalStateException();
    }

    // return currently available draw cards (Max: num of
    // Draw cards one can have at a time / numDraw)
    ArrayList<Card> temp = new ArrayList<>();
    for (int i = 0; i < this.maxDraw; i++) {
      if (this.draw[i] != null) {
        temp.add(this.draw[i]);
      }
    }
    return temp;
  }

  boolean gameNotStarted() {
    return this.gameState != GameState.Started;
  }

  boolean validCoordinates(int row, int card) {
    return row < this.maxRows
            && card <= row
            && card >= 0;
  }

  boolean invalidCardState(Card c) {
    return c.cardState != CardState.Exposed;
  }

  // can we expose any cards based on the given card being removed
  void expose(int row, int card) {

    // check left above aka (row - 1, card - 1)
    // by checking if card to c's left (row, card - 1) is removed
    // edge case: c is left-most card
    if (validCoordinates(row - 1, card - 1)) {
      // if left-above is valid, then left is valid
      Card update = this.getCardAt(row - 1, card - 1);
      Card cLeft = this.getCardAt(row, card - 1);
      if (cLeft.cardState == CardState.Removed) {
        update.cardState = CardState.Exposed;
      }
    }

    // check right above aka (row - 1, card)
    // by checking if card to c's right (row, card + 1) is removed
    // edge case: c is right-most card
    if (validCoordinates(row - 1, card)) {
      // if right-above is valid, then right is valid
      Card update = this.getCardAt(row - 1, card);
      Card cRight = this.getCardAt(row, card + 1);
      if (cRight.cardState == CardState.Removed) {
        update.cardState = CardState.Exposed;
      }
    }
  }

  boolean possThirteen() {
    ArrayList<Card> exposed = new ArrayList<>();
    for (int i = 0; i < this.maxRows; i++) {
      List<Card> t = this.pyramid.get(i);
      for (int j = 0; j < t.size(); j++) {
        Card c = this.getCardAt(i, j);
        if (c.cardState == CardState.Exposed) {
          exposed.add(c);
        }
      }
    }


    // todo: dont even need to check for this
    if (exposed.size() == 0 || this.getScore() == 0) {
      return false;
    }

    for (int i = 0; i < exposed.size(); i++) {
      for (int j = 0; j < exposed.size(); j++) {
        if (i != j) {
          Card iC = exposed.get(i);
          Card jC = exposed.get(j);

          if (iC.val.getValue() + jC.val.getValue() == 13) {
            return true;
          }
        }
      }
    }

    // 2) one exposed card from pyramid == 13
    for (Card card : exposed) {
      if (card.val.getValue() == 13) {
        return true;
      }
    }

    return false;
  }

}