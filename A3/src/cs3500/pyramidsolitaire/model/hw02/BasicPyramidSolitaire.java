package cs3500.pyramidsolitaire.model.hw02;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a basic implementation of the game PyramidSolitaire.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {

  private GameState gameState;
  private List<List<Card>> pyramid;
  private LinkedList<Card> stock;
  private int maxRows;
  private int maxDraw;
  private Card[] draw;

  /**
   * Implements the builder pattern for BasicPyramidSolitaire.
   */
  public static final class BPSBuilder {
    private GameState gameState;
    private List<List<Card>> pyramid;
    private LinkedList<Card> stock;
    private int maxRows;
    private int maxDraw;
    private Card[] draw;

    public BPSBuilder() {
      // Empty constructor. There are no static/final inputs for this builder.
    }

    BPSBuilder gameState(GameState gameState) {
      this.gameState = gameState;
      return this;
    }

    BPSBuilder pyramid(List<List<Card>> pyramid) {
      this.pyramid = pyramid;
      return this;
    }

    BPSBuilder stock(LinkedList<Card> stock) {
      this.stock = stock;
      return this;
    }

    BPSBuilder maxRows(int maxRows) {
      this.maxRows = maxRows;
      return this;
    }

    BPSBuilder maxDraw(int maxDraw) {
      this.maxDraw = maxDraw;
      return this;
    }

    BPSBuilder draw(Card[] draw) {
      this.draw = draw;
      return this;
    }

    BasicPyramidSolitaire build() {
      return new BasicPyramidSolitaire(this);
    }
  }

  /**
   * Instantiates a game of BasicPyramidSolitaire.
   */
  public BasicPyramidSolitaire() {
    this.gameState = GameState.NotStarted;
    this.pyramid = new ArrayList<>();
    this.stock = new LinkedList<>();
    this.maxRows = 0;
    this.maxDraw = 0;
    this.draw = new Card[0];
  }

  /**
   * Instantiates a game of BasicPyramidSolitaire using the Builder method.
   * Created for easier testing.
   * @param builder  the Builder.
   */
  private BasicPyramidSolitaire(BPSBuilder builder) {
    this.gameState = builder.gameState;
    this.pyramid = builder.pyramid;
    this.stock = builder.stock;
    this.maxRows = builder.maxRows;
    this.maxDraw = builder.maxDraw;
    this.draw = builder.draw;
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
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw) {

    if (invalidDeck(deck) || invalidRowsAndDraw(numRows, numDraw)) {
      throw new IllegalArgumentException("startGame: Invalid input.");
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }

    this.maxRows = numRows;
    this.maxDraw = numDraw;

    this.stock = new LinkedList<>(deck);
    this.sortIntoPyramid();
    this.sortIntoDraw();
    this.gameState = GameState.Started;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {

    if (!isGameOver()) {

      // game not started
      this.gameNotStartedError();


      // invalid input
      if (invalidCards(row1, card1, row2, card2)) {
        throw new IllegalArgumentException("rm2: Invalid input.");
      } else {

        // update gameState
        this.updateAndExpose(row1, card1);
        this.updateAndExpose(row2, card2);
      }

    }
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {

    if (!isGameOver()) {

      // game not started
      this.gameNotStartedError();


      // invalid input
      if (invalidCard(row, card) || invalidCombo(this.getCardAt(row, card), null)) {
        throw new IllegalArgumentException("rm1: Invalid input.");
      } else {
        this.updateAndExpose(row, card);
      }
    }
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {

    if (!isGameOver()) {

      // game not started
      this.gameNotStartedError();

      if (invalidCard(row, card) || invalidDrawIdx(drawIndex)
              || invalidCombo(this.getCardAt(row, card), this.draw[drawIndex])) {
        throw new IllegalArgumentException("rmwd: Invalid input.");
      } else {
        this.updateDrawPile(drawIndex);
        this.updateAndExpose(row, card);
      }
    }
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {

    if (!isGameOver()) {

      // game not started
      this.gameNotStartedError();

      if (invalidDrawIdx(drawIndex)) {
        throw new IllegalArgumentException("dd: Invalid input.");
      } else {
        this.updateDrawPile(drawIndex);
      }

    }
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
      validRow = false;
      counter = 0;
      while (!validRow && counter <= i) {
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
  public int getNumDraw() {
    if (this.gameState != GameState.Started) {
      return -1;
    } else {
      return this.maxDraw;
    }
  }

  @Override
  public int getRowWidth(int row) {
    this.gameNotStartedError();

    if (row >= this.maxRows) {
      throw new IllegalArgumentException("getRowWidth: Invalid row.");
    }

    return row + 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    this.gameNotStartedError();

    return this.gameWon() || this.gameLost();
  }

  @Override
  public int getScore() throws IllegalStateException {
    this.gameNotStartedError();

    int score = 0;
    for (int r = 0; r < this.maxRows; r++) {
      for (int i = 0; i <= r; i++) {
        if (this.pyramid.get(r).get(i) != null) {
          score += this.pyramid.get(r).get(i).getValue();
        }
      }
    }
    return score;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    this.gameNotStartedError();

    if (this.invalidCoordinates(row, card)) {
      throw new IllegalArgumentException("getCardAt: Invalid input.");
    }

    return this.pyramid.get(row).get(card);
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    this.gameNotStartedError();

    ArrayList<Card> temp = new ArrayList<>();
    temp.addAll(Arrays.asList(this.draw));
    return temp;

  }


  // helper methods
  // checks if the given deck is a valid deck.
  boolean invalidDeck(List<Card> deck) {
    if (deck == null || deck.size() != 52) {
      return true;
    } else {
      for (Symbol s : Symbol.values()) {
        for (Value v : Value.values()) {
          if (!deck.contains(new Card(s, v))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  // checks if the given inputs are valid for numRows and numDraw.
  boolean invalidRowsAndDraw(int numRows, int numDraw) {
    if (numDraw < 0 || numRows <= 0) {
      return true;
    }
    int total = 0;
    for (int i = 1; i <= numRows; i++) {
      total += i;
    }
    return total + numDraw > 52;
  }

  // checks if the given coordinates represents a valid card.
  boolean invalidCard(int row, int card) {
    return invalidCoordinates(row, card)
            || this.getCardAt(row, card) == null
            || this.getCardAt(row, card).cardState == CardState.NotExposed;
  }

  // checks if the given coordinates represents a valid card.
  boolean invalidCoordinates(int row, int card) {
    return row >= this.maxRows || card > row || card < 0;
  }

  // checks if the given coordinates represent valid cards.
  boolean invalidCards(int row1, int card1, int row2, int card2) {
    if (invalidCard(row1, card1) || invalidCard(row2, card2)) {
      return true;
    } else {
      Card c1 = this.getCardAt(row1, card1);
      Card c2 = this.getCardAt(row2, card2);
      return invalidCombo(c1, c2);
    }
  }

  // checks if the game hasn't been started.
  void gameNotStartedError() throws IllegalStateException {
    if (this.gameState == GameState.NotStarted) {
      throw new IllegalStateException();
    }
  }

  // checks if the given index is a valid drawIndex.
  boolean invalidDrawIdx(int drawIndex) {
    return drawIndex >= this.maxDraw
            || drawIndex < 0
            || this.maxDraw == 0
            || this.draw[drawIndex] == null;
  }

  // checks if the two given cards aren't a valid combo.
  boolean invalidCombo(Card c1, Card c2) {
    if (c1 == null && c2 == null) {
      return false;
    } else if (c2 == null) {
      return c1.getValue() != 13;
    } else {
      return c1.getValue() + c2.getValue() != 13;
    }
  }

  // updates the draw pile for discardDraw and removeUsingDraw.
  void updateDrawPile(int drawIndex) {
    this.draw[drawIndex].cardState = CardState.Removed;
    if (this.stock.size() > 0) {
      this.draw[drawIndex] = this.stock.pop();
    } else {
      this.draw[drawIndex] = null;
    }
  }

  // checks if the game has been won.
  boolean gameWon() {
    if (this.getScore() == 0) {
      this.gameState = GameState.Won;
      return true;
    } else {
      return false;
    }
  }

  // checks if the game has been lost.
  boolean gameLost() {
    if (this.cantRemoveOne()
            && this.cantRemoveTwo()
            && this.cantRemoveUsingDraw()
            && this.noMoreDrawCards()) {
      this.gameState = GameState.Lost;
      return true;
    } else {
      return false;
    }
  }

  // gets all the currently exposed cards.
  ArrayList<Card> getExposedCards() {
    ArrayList<Card> exposed = new ArrayList<>();
    for (int i = 0; i < this.maxRows; i++) {
      List<Card> t = this.pyramid.get(i);
      for (int j = 0; j < t.size(); j++) {
        if (t.get(j) != null && t.get(j).cardState == CardState.Exposed) {
          exposed.add(t.get(j));
        }
      }
    }

    return exposed;
  }

  // checks if you can remove any more cards using the remove (single) method.
  boolean cantRemoveOne() {
    ArrayList<Card> exposed = this.getExposedCards();
    for (Card card : exposed) {
      if (card.getValue() == 13) {
        return false;
      }
    }
    return true;
  }

  // checks if you can remove any more cards using the remove (pair-wise) method.
  boolean cantRemoveTwo() {
    ArrayList<Card> exposed = this.getExposedCards();
    for (int i = 0; i < exposed.size(); i++) {
      for (int j = 0; j < exposed.size(); j++) {
        if (i != j) {
          Card iC = exposed.get(i);
          Card jC = exposed.get(j);

          if (iC.getValue() + jC.getValue() == 13) {
            return false;
          }
        }
      }
    }
    return true;
  }

  // checks if you can remove any more cards using the removeUsingDraw method.
  boolean cantRemoveUsingDraw() {
    ArrayList<Card> exposed = this.getExposedCards();
    if (this.maxDraw == 0) {
      return true;
    }
    for (Card card : exposed) {
      for (Card draw : this.getDrawCards()) {

        if (draw != null && card.getValue() + draw.getValue() == 13) {
          return false;
        }
      }
    }
    return true;
  }

  // checks if there are no more draw cards left.
  boolean noMoreDrawCards() {

    // return # of currently available draw cards (Max: num of
    // Draw cards one can have at a time / numDraw)
    int count = 0;
    for (int i = 0; i < this.maxDraw; i++) {
      if (this.draw[i] != null) {
        count++;
      }
    }
    return count == 0;
  }

  // can we expose any cards based on the given card being removed
  void updateAndExpose(int row, int card) {

    this.pyramid.get(row).set(card, null);

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

  // todo: added clear statement
  // sorts appropriate cards into pyramid field.
  void sortIntoPyramid() {
    this.pyramid.clear();
    for (int r = 0; r < this.maxRows; r++) {
      ArrayList<Card> row = new ArrayList<>();
      for (int i = 0; i <= r; i++) {
        Card c = this.stock.pop();
        if (r == this.maxRows - 1) {
          c.cardState = CardState.Exposed;
        } else {
          c.cardState = CardState.NotExposed;
        }
        row.add(c);
      }
      this.pyramid.add(row);
    }
  }

  // sorts appropriate cards into draw field.
  void sortIntoDraw() {
    if (this.maxDraw > 0) {
      this.draw = new Card[this.maxDraw];
      for (int d = 0; d < this.maxDraw; d++) {
        this.draw[d] = this.stock.pop();
      }
    } else {
      this.draw = new Card[0];
    }
  }
}
