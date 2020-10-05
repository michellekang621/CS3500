import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;
import cs3500.pyramidsolitaire.model.hw04.AbstractPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TripeaksPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
/**
 * Tests for public method implementations of PyramidSolitaireModels.
 * Different implementations should pass different tests.
 */
public class AbstractPyramidSolitaireUniqueTest {

  AbstractPyramidSolitaire bps;
  AbstractPyramidSolitaire tps;
  AbstractPyramidSolitaire rps;

  private List<Card> deck;

  private List<Card> draw = new ArrayList<>();

  private void resetBPS() {
    this.bps = new BasicPyramidSolitaire();
    bps.startGame(bps.getDeck(), false, 7, 3);
  }

  private void resetTPS() {
    this.tps = new TripeaksPyramidSolitaire();
    tps.startGame(tps.getDeck(), false, 7, 3);
  }

  private void resetRPS() {
    this.rps = new RelaxedPyramidSolitaire();
    rps.startGame(rps.getDeck(), false, 7, 3);
  }

  private void resetDeck() {
    // clubs
    Card c1 = new Card(Symbol.Club, Value.Ace);
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
    Card d13 = new Card(Symbol.Diamond, Value.King);

    // hearts
    Card h1 = new Card(Symbol.Heart, Value.Ace);
    Card h2 = new Card(Symbol.Heart, Value.Two);
    Card h3 = new Card(Symbol.Heart, Value.Three);
    Card h4 = new Card(Symbol.Heart, Value.Four);
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

    this.deck =
            new ArrayList<>(Arrays.asList(
                    c1, c2, c3, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, d13,
                    h1, h2, h3, h4, h5, h6,
                    h7, h8, h9, h10, h11, h12, h13,
                    s1, s2, s3, s4, s5, s6,
                    s7, s8, s9, s10, s11, s12, s13));
  }

  private void resetDoubleDeck() {
    this.resetDeck();
    ArrayList<Card> doubleDeck = new ArrayList<>();
    doubleDeck.addAll(this.deck);
    doubleDeck.addAll(this.deck);
    this.deck.clear();
    this.deck.addAll(doubleDeck);
  }

  @Test
  public void getDeckBPS() {
    this.resetBPS();
    this.resetDeck();
    assertEquals(this.bps.getDeck(), this.deck);
  }

  @Test
  public void getDeckTPS() {
    this.resetTPS();
    this.resetDoubleDeck();
    assertEquals(this.tps.getDeck(), this.deck);
  }

  @Test
  public void getDeckRPS() {
    this.resetRPS();
    this.resetDeck();
    assertEquals(this.rps.getDeck(), this.deck);
  }

  // deck is invalid
  @Test(expected = IllegalArgumentException.class)
  public void startGameBPS1() {
    this.resetDeck();
    this.deck.remove(0);
    this.bps = new BasicPyramidSolitaire();
    bps.startGame(this.deck, false, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTPS1() {
    this.resetDeck();
    this.tps = new TripeaksPyramidSolitaire();
    tps.startGame(this.deck, false, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameRPS1() {
    this.resetDeck();
    this.deck.remove(0);
    this.rps = new RelaxedPyramidSolitaire();
    rps.startGame(this.deck, false, 7, 3);
  }

  // everything is valid & shuffled
  @Test
  public void startGameBPS2() {
    this.resetBPS();
    this.resetDeck();
    this.bps.startGame(this.bps.getDeck(), true, 7, 3);
    assertNotEquals(this.deck, this.bps.getDrawCards());
    assertEquals(4, this.bps.getRowWidth(3));
  }

  @Test
  public void startGameTPS2() {
    this.resetTPS();
    this.resetDoubleDeck();
    this.tps.startGame(this.tps.getDeck(), true, 7, 3);
    assertNotEquals(this.deck, this.tps.getDrawCards());
    assertEquals(10, this.tps.getRowWidth(3));
  }

  @Test
  public void startGameRPS2() {
    this.resetRPS();
    this.resetDeck();
    this.rps.startGame(this.rps.getDeck(), true, 7, 3);
    assertNotEquals(this.deck, this.rps.getDrawCards());
    assertEquals(4, this.rps.getRowWidth(3));
  }

  // everything is valid & not shuffled
  @Test
  public void startGameBPS3() {
    this.resetBPS();
    this.resetDeck();
    this.bps.startGame(this.bps.getDeck(), false, 7, 3);
    assertEquals(4, this.bps.getRowWidth(3));
    assertEquals(185, this.bps.getScore());
    assertEquals(new Card(Symbol.Diamond, Value.King), this.bps.getCardAt(6, 4));
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    this.draw.add(new Card(Symbol.Heart, Value.Five));
    assertEquals(this.draw, this.bps.getDrawCards());
  }

  @Test
  public void startGameTPS3() {
    this.resetTPS();
    this.resetDoubleDeck();
    this.tps.startGame(this.tps.getDeck(), false, 7, 3);
    assertEquals(10, this.tps.getRowWidth(3));
    assertEquals(442, this.tps.getScore());
    assertEquals(new Card(Symbol.Club, Value.Four), this.tps.getCardAt(6, 4));
    this.draw.add(new Card(Symbol.Club, Value.King));
    this.draw.add(new Card(Symbol.Diamond, Value.Ace));
    this.draw.add(new Card(Symbol.Diamond, Value.Two));
    assertEquals(this.draw, this.tps.getDrawCards());
  }

  @Test
  public void startGameRPS3() {
    this.resetRPS();
    this.resetDeck();
    this.rps.startGame(this.rps.getDeck(), false, 7, 3);
    assertEquals(4, this.rps.getRowWidth(3));
    assertEquals(185, this.rps.getScore());
    assertEquals(new Card(Symbol.Diamond, Value.King), this.rps.getCardAt(6, 4));
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    this.draw.add(new Card(Symbol.Heart, Value.Five));
    assertEquals(this.draw, this.rps.getDrawCards());
  }

  // calling startGame twice
  @Test
  public void startGameBPS4() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.bps.remove(6, 2, 6, 6);
    assertEquals(172, this.bps.getScore());
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
  }

  @Test
  public void startGameTPS4() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    this.tps.remove(6, 1, 6, 12);
    assertEquals(429, this.tps.getScore());
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(442, this.tps.getScore());
  }

  @Test
  public void startGameRPS4() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    this.rps.remove(6, 2, 6, 6);
    assertEquals(172, this.rps.getScore());
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
  }

  @Test
  public void removeTwoBPS1() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
    this.bps.remove(6, 2, 6, 6);
    assertEquals(172, this.bps.getScore());
  }

  @Test
  public void removeTwoTPS1() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(442, this.tps.getScore());
    this.tps.remove(6, 1, 6, 12);
    assertEquals(429, this.tps.getScore());
  }

  @Test
  public void removeTwoRPS1() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
    this.rps.remove(6, 2, 6, 6);
    assertEquals(172, this.rps.getScore());
  }

  // case where removing two cards would throw an error for BPS but not for RPS
  // as seen in the two tests below
  // todo
  @Test(expected = IllegalArgumentException.class)
  public void removeTwoBPS2() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
    this.bps.remove(6, 2, 6, 6);
    assertEquals(172, this.bps.getScore());
    this.bps.remove(6, 2, 6, 6);
  }

  // todo
  @Test
  public void removeTwoRPS2() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
    this.rps.remove(6, 2, 6, 6);
    assertEquals(172, this.rps.getScore());
  }

  @Test
  public void removeOneBPS1() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
    this.bps.remove(6, 4);
    assertEquals(172, this.bps.getScore());
  }

  @Test
  public void removeOneTPS1() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(442, this.tps.getScore());
    this.tps.remove(6, 0);
    assertEquals(429, this.tps.getScore());
  }

  @Test
  public void removeOneRPS1() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
    this.rps.remove(6, 4);
    assertEquals(172, this.rps.getScore());
  }

  // case where removing a specific card would throw an error for BPS but not for RPS
  // as seen in the two tests below
  // todo
  @Test(expected = IllegalArgumentException.class)
  public void removeOneBPS2() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
    this.bps.remove(6, 4);
    assertEquals(172, this.bps.getScore());
    this.bps.remove(5, 4);
  }

  // todo
  @Test
  public void removeOneRPS2() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
    this.rps.remove(6, 4);
    assertEquals(172, this.rps.getScore());
    this.rps.remove(5, 4, 5, 3);
    assertEquals(159, this.rps.getScore());
  }

  @Test
  public void removeUsingDrawBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.bps.removeUsingDraw(0, 6, 1);
    assertEquals(175, this.bps.getScore());
  }

  @Test
  public void removeUsingDrawTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    PyramidSolitaireTextualView pstv = new PyramidSolitaireTextualView(this.tps);
    System.out.println(pstv.toString());
    System.out.println(this.tps.getRowWidth(0));

    this.tps.discardDraw(0);
    this.tps.removeUsingDraw(0, 6, 12);
    assertEquals(430, this.tps.getScore());
  }

  @Test
  public void removeUsingDrawRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    this.rps.removeUsingDraw(0, 6, 1);
    assertEquals(175, this.rps.getScore());
  }

  @Test
  public void discardDrawBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 3);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    this.draw.add(new Card(Symbol.Heart, Value.Five));
    assertEquals(this.draw, this.bps.getDrawCards());
    this.bps.discardDraw(0);
    assertNotEquals(this.draw, this.bps.getDrawCards());

    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    assertEquals(this.draw, this.bps.getDrawCards());
    this.bps.discardDraw(0);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    assertEquals(draw, this.bps.getDrawCards());
  }

  @Test
  public void discardDrawTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 3);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Club, Value.King));
    this.draw.add(new Card(Symbol.Diamond, Value.Ace));
    this.draw.add(new Card(Symbol.Diamond, Value.Two));
    assertEquals(this.draw, this.tps.getDrawCards());
    this.tps.discardDraw(0);
    assertNotEquals(this.draw, this.tps.getDrawCards());

    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    this.tps.discardDraw(0);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Diamond, Value.Ace));
    assertEquals(draw, this.tps.getDrawCards());
  }

  @Test
  public void discardDrawRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 3);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    this.draw.add(new Card(Symbol.Heart, Value.Five));
    assertEquals(this.draw, this.rps.getDrawCards());
    this.rps.discardDraw(0);
    assertNotEquals(this.draw, this.rps.getDrawCards());
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    this.rps.discardDraw(0);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    assertEquals(this.draw, this.rps.getDrawCards());
  }

  @Test
  public void getNumRowsBPS() {
    // # of rows originally in pyramid
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 3, 1);
    assertEquals(3, this.bps.getNumRows());
    // # of rows after game has been played
    this.bps.removeUsingDraw(0, 2, 2);
    this.bps.removeUsingDraw(0, 2, 1);
    this.bps.removeUsingDraw(0, 2, 0);
    assertEquals(2, this.bps.getNumRows());
    assertNotEquals(3, this.bps.getNumRows());
  }

  @Test
  public void getNumRowsTPS() {
    // # of rows originally in pyramid
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 3, 1);
    PyramidSolitaireTextualView pstv = new PyramidSolitaireTextualView(this.tps);
    System.out.println(pstv.toString());
    assertEquals(3, this.tps.getNumRows());
    // # of rows after game has been played
    this.tps.discardDraw(0);
    this.tps.removeUsingDraw(0, 2, 4);
    this.tps.removeUsingDraw(0, 2, 3);
    this.tps.removeUsingDraw(0, 2, 2);
    assertEquals(3, this.tps.getNumRows());
    assertNotEquals(4, this.tps.getNumRows());
  }

  @Test
  public void getNumRowsRPS() {
    // # of rows originally in pyramid
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 3, 1);
    assertEquals(3, this.rps.getNumRows());
    // # of rows after game has been played
    this.rps.removeUsingDraw(0, 2, 2);
    this.rps.removeUsingDraw(0, 2, 1);
    this.rps.removeUsingDraw(0, 2, 0);
    assertEquals(2, this.rps.getNumRows());
    assertNotEquals(3, this.rps.getNumRows());
  }

  @Test
  public void getRowWidthBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(1, this.bps.getRowWidth(0));
    assertEquals(7, this.bps.getRowWidth(6));
  }

  @Test
  public void getRowWidthTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(7, this.tps.getRowWidth(0));
    assertEquals(13, this.tps.getRowWidth(6));
  }

  @Test
  public void getRowWidthRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(1, this.rps.getRowWidth(0));
    assertEquals(7, this.rps.getRowWidth(6));
  }

  // game is over (won)
  @Test
  public void isGameOverBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 1, 12);
    System.out.println(new PyramidSolitaireTextualView(this.bps).toString());
    // removeWithDraw
    this.bps.removeUsingDraw(10, 0, 0);
    assertTrue(this.bps.isGameOver());
  }

  @Test
  public void isGameOverTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 1, 12);
    System.out.println(new PyramidSolitaireTextualView(this.tps).toString());
    // removeWithDraw
    this.tps.removeUsingDraw(10, 0, 0);
    assertTrue(this.tps.isGameOver());
  }

  @Test
  public void isGameOverRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 1, 12);
    System.out.println(new PyramidSolitaireTextualView(this.rps).toString());
    // removeWithDraw
    this.rps.removeUsingDraw(10, 0, 0);
    assertTrue(this.rps.isGameOver());
  }

  @Test
  public void getScoreBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(185, this.bps.getScore());
    this.bps.remove(6, 2, 6, 6);
    assertEquals(172, this.bps.getScore());
  }

  @Test
  public void getScoreTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(442, this.tps.getScore());
    this.tps.remove(6, 1, 6, 12);
    assertEquals(429, this.tps.getScore());
  }

  @Test
  public void getScoreRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(185, this.rps.getScore());
    this.rps.remove(6, 2, 6, 6);
    assertEquals(172, this.rps.getScore());
  }

  @Test
  public void getCardAtBPS1() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    assertEquals(new Card(Symbol.Club, Value.Ace), this.bps.getCardAt(0, 0));
  }

  @Test
  public void getCardAtTPS1() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    assertEquals(new Card(Symbol.Club, Value.Ace), this.tps.getCardAt(0, 0));
  }

  @Test
  public void getCardAtRPS1() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    assertEquals(new Card(Symbol.Club, Value.Ace), this.rps.getCardAt(0, 0));
  }

  // card at this location is null
  @Test
  public void getCardAtBPS2() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.bps.remove(6, 4);
    assertEquals(null, this.bps.getCardAt(6, 4));
  }

  @Test
  public void getCardAtTPS2() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    this.tps.remove(6, 0);
    assertEquals(null, this.tps.getCardAt(6, 0));
  }

  @Test
  public void getCardAtRPS2() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    this.rps.remove(6, 4);
    assertEquals(null, this.rps.getCardAt(6, 4));
  }

  @Test
  public void getDrawCardsBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    assertEquals(this.draw, this.bps.getDrawCards());
    this.bps.discardDraw(0);
    this.draw.clear();
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    assertEquals(draw, this.bps.getDrawCards());
  }

  @Test
  public void getDrawCardsTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Club, Value.King));
    assertEquals(this.draw, this.tps.getDrawCards());
    this.tps.discardDraw(0);
    this.draw.clear();
    this.draw.add(new Card(Symbol.Diamond, Value.Ace));
    assertEquals(draw, this.tps.getDrawCards());
  }

  @Test
  public void getDrawCardsRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Heart, Value.Three));
    assertEquals(this.draw, this.rps.getDrawCards());
    this.rps.discardDraw(0);
    this.draw.clear();
    this.draw.add(new Card(Symbol.Heart, Value.Four));
    assertEquals(this.draw, this.rps.getDrawCards());
  }
}