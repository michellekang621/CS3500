import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Value;
import cs3500.pyramidsolitaire.model.hw02.Symbol;

/**
 * Tests that all the methods in BasicPyramidSolitaire work correctly.
 */
public class BasicPyramidSolitaireTest {

  private BasicPyramidSolitaire bps;

  private List<Card> deck;
  private List<Card> fullDeck;
  private List<Card> fullMutatedDeck;

  private Card c1;
  private Card d13;
  private Card h3;
  private Card h4;

  private void reset() {
    this.bps = new BasicPyramidSolitaire();
    this.deck = new ArrayList<>();

    // clubs
    this.c1 = new Card(Symbol.Club, Value.Ace);
    Card c2 = new Card(Symbol.Club, Value.Two);
    Card c3 = new Card(Symbol.Club, Value.Three);
    Card c4 = new Card(Symbol.Club, Value.Four);
    Card c5 = new Card(Symbol.Club, Value.Five);
    Card c6 = new Card(Symbol.Club, Value.Six);
    Card c7 = new Card(Symbol.Club, Value.Seven);
    Card c8 = new Card(Symbol.Club, Value.Eight);
    Card c9 = new Card(Symbol.Club, Value.Nine);
    Card c10 = new Card(Symbol.Club, Value.Ten);
    Card c11 = new Card(Symbol.Club, Value.Jack);
    Card c12 = new Card(Symbol.Club, Value.Queen);
    Card c13 = new Card(Symbol.Club, Value.King);

    // diamonds
    Card d1 = new Card(Symbol.Diamond, Value.Ace);
    Card d2 = new Card(Symbol.Diamond, Value.Two);
    Card d3 = new Card(Symbol.Diamond, Value.Three);
    Card d4 = new Card(Symbol.Diamond, Value.Four);
    Card d5 = new Card(Symbol.Diamond, Value.Five);
    Card d6 = new Card(Symbol.Diamond, Value.Six);
    Card d7 = new Card(Symbol.Diamond, Value.Seven);
    Card d8 = new Card(Symbol.Diamond, Value.Eight);
    Card d9 = new Card(Symbol.Diamond, Value.Nine);
    Card d10 = new Card(Symbol.Diamond, Value.Ten);
    Card d11 = new Card(Symbol.Diamond, Value.Jack);
    Card d12 = new Card(Symbol.Diamond, Value.Queen);
    this.d13 = new Card(Symbol.Diamond, Value.King);

    // hearts
    Card h1 = new Card(Symbol.Heart, Value.Ace);
    Card h2 = new Card(Symbol.Heart, Value.Two);
    this.h3 = new Card(Symbol.Heart, Value.Three);
    this.h4 = new Card(Symbol.Heart, Value.Four);
    Card h5 = new Card(Symbol.Heart, Value.Five);
    Card h6 = new Card(Symbol.Heart, Value.Six);
    Card h7 = new Card(Symbol.Heart, Value.Seven);
    Card h8 = new Card(Symbol.Heart, Value.Eight);
    Card h9 = new Card(Symbol.Heart, Value.Nine);
    Card h10 = new Card(Symbol.Heart, Value.Ten);
    Card h11 = new Card(Symbol.Heart, Value.Jack);
    Card h12 = new Card(Symbol.Heart, Value.Queen);
    Card h13 = new Card(Symbol.Heart, Value.King);

    // spades
    Card s1 = new Card(Symbol.Spade, Value.Ace);
    Card s2 = new Card(Symbol.Spade, Value.Two);
    Card s3 = new Card(Symbol.Spade, Value.Three);
    Card s4 = new Card(Symbol.Spade, Value.Four);
    Card s5 = new Card(Symbol.Spade, Value.Five);
    Card s6 = new Card(Symbol.Spade, Value.Six);
    Card s7 = new Card(Symbol.Spade, Value.Seven);
    Card s8 = new Card(Symbol.Spade, Value.Eight);
    Card s9 = new Card(Symbol.Spade, Value.Nine);
    Card s10 = new Card(Symbol.Spade, Value.Ten);
    Card s11 = new Card(Symbol.Spade, Value.Jack);
    Card s12 = new Card(Symbol.Spade, Value.Queen);
    Card s13 = new Card(Symbol.Spade, Value.King);

    this.fullDeck =
            new ArrayList<>(Arrays.asList(
                    this.c1, c2, c3, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, this.d13,
                    h1, h2, this.h3, this.h4, h5, h6,
                    h7, h8, h9, h10, h11, h12, h13,
                    s1, s2, s3, s4, s5, s6,
                    s7, s8, s9, s10, s11, s12, s13));

    this.fullMutatedDeck =
            new ArrayList<>(Arrays.asList(
                    this.c1, c3, c2, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, this.d13,
                    h1, h2, this.h3, this.h4, h5, h6,
                    h7, h8, h9, h10, h11, h12, h13,
                    s1, s2, s3, s4, s5, s6,
                    s7, s8, s9, s10, s11, s12, s13));

    List<Card> shuffledDeck = new ArrayList<>(fullDeck);
    Collections.shuffle(shuffledDeck, new Random(1));
  }

  // TESTING: GETDECK()
  @Test
  public void getDeck() {
    this.reset();
    assertEquals(this.fullDeck, this.bps.getDeck());
    //assertEquals(this.fullMutatedDeck, this.bps.getDeck());

  }

  // TESTING: STARTGAME()
  // deck is null
  @Test(expected = IllegalArgumentException.class)
  public void startGameOne() {
    this.reset();
    this.bps.startGame(null, true, 3, 3);
  }

  // deck is invalid
  @Test(expected = IllegalArgumentException.class)
  public void startGameTwo() {
    this.reset();
    this.deck.add(this.c1);
    this.bps.startGame(this.deck, false, 5, 2);
  }

  // another input is invalid
  @Test(expected = IllegalArgumentException.class)
  public void startGameThree() {
    this.reset();
    this.bps.startGame(this.deck, false, 7, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameFour() {
    this.reset();
    this.bps.startGame(this.deck, false, -1, 1);
  }

  // todo: everything is valid & shuffled
  @Test
  public void startGameFive() {
    this.reset();
    this.bps.startGame(this.fullDeck, true, 7, 1);
    assertEquals(7, this.bps.getNumRows());
    assertEquals(1, this.bps.getNumDraw());
    assertEquals(4, this.bps.getRowWidth(3));
    assertFalse(this.bps.isGameOver());
  }

  @Test
  public void startGameFivePartTwo() {
    this.reset();
    this.bps.startGame(this.fullMutatedDeck, true, 7, 1);
    assertEquals(7, this.bps.getNumRows());
    assertEquals(1, this.bps.getNumDraw());
    assertEquals(4, this.bps.getRowWidth(3));
    assertFalse(this.bps.isGameOver());
  }

  // everything is valid & not shuffled
  @Test
  public void startGameSix() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(7, this.bps.getNumRows());
    assertEquals(1, this.bps.getNumDraw());
    assertEquals(4, this.bps.getRowWidth(3));
    assertFalse(this.bps.isGameOver());
    assertEquals(185, this.bps.getScore());
    assertEquals(this.d13, this.bps.getCardAt(6, 4));
    this.deck.add(this.h3);
    assertEquals(this.deck, this.bps.getDrawCards());
  }


  // TESTING: REMOVE() (with 2 pyramid cards)
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void removeOne() {
    this.reset();
    this.bps.remove(6, 2, 6, 6);
  }

  // move is invalid
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, 1, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeTwoPartTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(5, 0, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(-1, 0, -3, 6);
  }

  // pairwise move
  @Test
  public void removeFour() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, 2, 6, 6);
    assertEquals(172, this.bps.getScore());
  }

  // TESTING: REMOVE() (with 1 pyramid card)
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void removeFive() {
    this.reset();
    this.bps.remove(6, 4);
  }

  // move is invalid
  @Test(expected = IllegalArgumentException.class)
  public void removeSix() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeSixPartTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, -4);
  }

  // single-card move
  @Test
  public void removeSeven() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, 4);
    assertEquals(172, this.bps.getScore());
  }

  // TESTING: REMOVEUSINGDRAW()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void removeUsingDrawOne() {
    this.reset();
    this.bps.removeUsingDraw(0, 6, 1);

  }

  // move is invalid
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.removeUsingDraw(0, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.removeUsingDraw(3, 6, 2);
  }

  // pairwise move
  @Test
  public void removeUsingDrawFour() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.removeUsingDraw(0, 6, 1);
    assertEquals(175, this.bps.getScore());
  }

  // TESTING: DISCARDDRAW()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void discardDrawOne() {
    this.reset();
    this.bps.discardDraw(0);
  }

  // move is invalid
  @Test(expected = IllegalArgumentException.class)
  public void discardDrawTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.discardDraw(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 0);
    this.bps.discardDraw(0);
  }

  // discard individual card from draw pile
  @Test
  public void discardDrawFour() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.discardDraw(0);
    ArrayList<Card> draw = new ArrayList<>();
    draw.add(h4);
    assertEquals(draw, this.bps.getDrawCards());
  }

  @Test
  public void getNumRows() {
    // game hasn't been started (output: -1)
    this.reset();
    assertEquals(-1, this.bps.getNumRows());
    // # of rows originally in pyramid
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(7, this.bps.getNumRows());

  }

  @Test
  public void getNumDraw() {
    // game hasn't been started (output: -1)
    this.reset();
    assertEquals(-1, this.bps.getNumDraw());
    // max # of visible cards in the draw pile
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(1, this.bps.getNumDraw());
  }

  // TESTING: GETROWWIDTH()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void getRowWidthOne() {
    this.reset();
    this.bps.getRowWidth(0);
  }

  // row is invalid
  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.getRowWidth(10);
  }

  // # of cards dealt into the given row
  @Test
  public void getRowWidthThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(1, this.bps.getRowWidth(0));
  }

  // TESTING: ISGAMEOVER()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void isGameOverOne() {
    this.reset();
    this.bps.isGameOver();
  }

  // game is over (output: true)
  @Test
  public void isGameOverTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 1, 1);
    assertFalse(this.bps.isGameOver());
  }

  // game isn't over (output: false)
  @Test
  public void isGameOverThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.isGameOver());
  }

  // TESTING GETSCORE()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void getScoreOne() {
    this.reset();
    this.bps.getScore();
  }

  // sum of values of cards remaining in pyramid
  @Test
  public void getScoreTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(185, this.bps.getScore());
  }

  // TESTING: GETCARDAT()
  @Test(expected = IllegalStateException.class)
  // game hasn't been started
  public void getCardAtOne() {
    this.reset();
    this.bps.getCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  // coordinates are invalid
  public void getCardAtTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.getCardAt(9, 0);
  }

  @Test
  // return card at the specified coordinates
  public void getCardAtThree() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(this.c1, this.bps.getCardAt(0, 0));
  }

  // TESTING GETDRAWCARDS()
  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void getDrawCardsOne() {
    this.reset();
    this.bps.getDrawCards();
  }

  // ordered list of available draw cards (should be @ most .getNumDraw() cards)
  @Test
  public void getDrawCardsTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    ArrayList<Card> draw = new ArrayList<>();
    draw.add(this.h3);
    assertEquals(draw, this.bps.getDrawCards());
  }

  @Test
  public void playingGame() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    this.bps.remove(6, 2, 6, 6);
    assertEquals(1, this.bps.getNumDraw());
  }
}