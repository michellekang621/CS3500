package cs3500.pyramidsolitaire.model.hw04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for non-public method implementations of PyramidSolitaireModels.
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
  public void invalidDeckBPS() {
    this.resetBPS();
    this.resetDeck();
    assertFalse(this.bps.invalidDeck(this.deck));

    this.resetDoubleDeck();
    assertTrue(this.bps.invalidDeck(this.deck));
  }

  @Test
  public void invalidDeckTPS() {
    this.resetTPS();
    this.resetDoubleDeck();
    assertFalse(this.tps.invalidDeck(this.deck));

    this.resetDeck();
    assertTrue(this.tps.invalidDeck(this.deck));

  }

  @Test
  public void invalidDeckRPS() {
    this.resetRPS();
    this.resetDeck();
    assertFalse(this.rps.invalidDeck(this.deck));

    this.resetDoubleDeck();
    assertTrue(this.rps.invalidDeck(this.deck));
  }

  @Test
  public void invalidRowsAndDrawBPS() {
    this.resetBPS();
    this.resetDeck();
    assertFalse(this.bps.invalidRowsAndDraw(7, 24));

    this.resetDoubleDeck();
    assertTrue(this.bps.invalidRowsAndDraw(8, 24));
  }

  @Test
  public void invalidRowsAndDrawTPS() {
    this.resetTPS();
    this.resetDoubleDeck();
    assertFalse(this.tps.invalidRowsAndDraw(7, 40));

    this.resetDeck();
    assertTrue(this.tps.invalidRowsAndDraw(8, 40));
  }

  @Test
  public void invalidRowsAndDrawRPS() {
    this.resetRPS();
    this.resetDeck();
    assertFalse(this.rps.invalidRowsAndDraw(7, 24));

    this.resetDoubleDeck();
    assertTrue(this.rps.invalidRowsAndDraw(8, 24));
  }

  @Test
  public void invalidCardBPS() {
    this.resetBPS();
    this.bps.remove(6, 4);
    assertTrue(this.bps.invalidCard(6, 7));
    assertTrue(this.bps.invalidCard(5, 4));
    assertTrue(this.bps.invalidCard(6, 4));
  }

  @Test
  public void invalidCardTPS() {
    this.resetTPS();
    this.tps.remove(6, 0);
    assertTrue(this.tps.invalidCard(6, 13));
    assertFalse(this.tps.invalidCard(6, 7));
    assertTrue(this.tps.invalidCard(4, 4));
  }

  @Test
  public void invalidCardRPS() {
    this.resetRPS();
    this.rps.remove(6, 4);
    assertTrue(this.rps.invalidCard(6, 7));
    assertFalse(this.rps.invalidCard(5, 4));
    assertTrue(this.rps.invalidCard(3, 3));
    assertTrue(this.rps.invalidCard(6, 4));
  }

  @Test
  public void invalidCoordinatesBPS() {
    this.resetBPS();
    this.bps.remove(6, 4);
    assertTrue(this.bps.invalidCoordinates(6, 7));
  }

  @Test
  public void invalidCoordinatesTPS() {
    this.resetTPS();
    assertTrue(this.tps.invalidCoordinates(6, 13));
    assertFalse(this.tps.invalidCoordinates(6, 7));
  }

  @Test
  public void invalidCoordinatesRPS() {
    this.resetRPS();
    this.rps.remove(6, 4);
    assertTrue(this.rps.invalidCoordinates(6, 7));
  }

  @Test
  public void invalidCardsBPS() {
    this.resetBPS();
    this.bps.remove(6, 4);
    // one of the cards are null
    assertTrue(this.bps.invalidCards(6, 4, 6, 3));
    // cards don't add up to 13
    assertTrue(this.bps.invalidCards(6, 3, 6, 1));
    // valid inputs
    assertFalse(this.bps.invalidCards(6, 2, 6, 6));
  }

  @Test
  public void invalidCardsTPS() {
    this.resetTPS();
    this.tps.remove(6, 0);
    // one of the cards are null
    assertTrue(this.tps.invalidCards(6, 4, 6, 3));
    // cards don't add up to 13
    assertTrue(this.tps.invalidCards(6, 3, 6, 1));
    // valid inputs
    assertFalse(this.tps.invalidCards(6, 1, 6, 12));

  }

  @Test
  public void invalidCardsRPS() {
    this.resetRPS();
    this.rps.remove(6, 4);
    // one of the cards are null
    assertTrue(this.rps.invalidCards(6, 4, 6, 3));
    // cards don't add up to 13
    assertTrue(this.rps.invalidCards(6, 3, 6, 1));
    // valid inputs
    assertFalse(this.rps.invalidCards(6, 2, 6, 6));

  }

  @Test
  public void invalidDrawIdxBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    for (int i = 0; i < 24; i++) {
      this.bps.discardDraw(0);
    }
    // draw idx is null
    assertTrue(this.bps.invalidDrawIdx(0));
  }

  @Test
  public void invalidDrawIdxTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 7, 1);
    for (int i = 0; i < 40; i++) {
      this.tps.discardDraw(0);
    }
    // draw idx is null
    assertTrue(this.tps.invalidDrawIdx(0));
  }

  @Test
  public void invalidDrawIdxRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 7, 1);
    for (int i = 0; i < 24; i++) {
      this.rps.discardDraw(0);
    }
    // draw idx is null
    assertTrue(this.rps.invalidDrawIdx(0));
  }

  @Test
  public void updateDrawPileBPS() {
    this.resetBPS();
    assertEquals(new Card(Symbol.Heart, Value.Three), this.bps.getDrawCards().get(0));
    this.bps.updateDrawPile(0);
    assertNotEquals(new Card(Symbol.Club, Value.Three), this.bps.getDrawCards().get(0));
  }

  @Test
  public void updateDrawPileTPS() {
    this.resetTPS();
    assertEquals(new Card(Symbol.Club, Value.King), this.tps.getDrawCards().get(0));
    this.tps.updateDrawPile(0);
    assertNotEquals(new Card(Symbol.Club, Value.King), this.tps.getDrawCards().get(0));

  }

  @Test
  public void updateDrawPileRPS() {
    this.resetRPS();
    assertEquals(new Card(Symbol.Heart, Value.Three), this.rps.getDrawCards().get(0));
    this.rps.updateDrawPile(0);
    assertNotEquals(new Card(Symbol.Heart, Value.Three), this.rps.getDrawCards().get(0));
    assertNotEquals(new Card(Symbol.Heart, Value.Four), this.rps.getDrawCards().get(0));
  }

  @Test
  public void gameWonBPS() {
    this.resetBPS();
    this.bps.startGame(this.bps.getDeck(), false, 1, 13);
    assertFalse(this.bps.isGameOver());
    System.out.println(new PyramidSolitaireTextualView(this.bps).toString());
    this.bps.removeUsingDraw(10, 0, 0);
    assertTrue(this.bps.isGameOver());
  }

  @Test
  public void gameWonTPS() {
    this.resetTPS();
    this.tps.startGame(this.tps.getDeck(), false, 1, 13);
    assertFalse(this.tps.isGameOver());
    System.out.println(new PyramidSolitaireTextualView(this.tps).toString());
    this.tps.removeUsingDraw(10, 0, 0);
    assertTrue(this.tps.isGameOver());
  }

  @Test
  public void gameWonRPS() {
    this.resetRPS();
    this.rps.startGame(this.rps.getDeck(), false, 1, 13);
    assertFalse(this.rps.isGameOver());
    System.out.println(new PyramidSolitaireTextualView(this.rps).toString());
    this.rps.removeUsingDraw(10, 0, 0);
    assertTrue(this.rps.isGameOver());
  }

  @Test
  public void getExposedCardsBPS() {
    this.resetBPS();
    bps.startGame(bps.getDeck(), false, 2, 7);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Club, Value.Two));
    exposed.add(new Card(Symbol.Club, Value.Three));
    assertEquals(exposed, this.bps.getExposedCards());
    this.bps.removeUsingDraw(6,1, 1);
    assertNotEquals(exposed, this.bps.getExposedCards());
    exposed.remove(1);
    assertEquals(exposed, this.bps.getExposedCards());
  }

  @Test
  public void getExposedCardsTPS() {
    this.resetTPS();
    tps.startGame(tps.getDeck(), false, 2, 1);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Club, Value.Four));
    exposed.add(new Card(Symbol.Club, Value.Five));
    exposed.add(new Card(Symbol.Club, Value.Six));
    exposed.add(new Card(Symbol.Club, Value.Seven));
    assertEquals(exposed, this.tps.getExposedCards());
    this.tps.removeUsingDraw(0,1, 1);
    assertNotEquals(exposed, this.tps.getExposedCards());
    exposed.remove(1);
    assertEquals(exposed, this.tps.getExposedCards());
  }

  @Test
  public void getExposedCardsRPS() {
    this.resetRPS();
    rps.startGame(rps.getDeck(), false, 2, 7);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Club, Value.Two));
    exposed.add(new Card(Symbol.Club, Value.Three));
    assertEquals(exposed, this.rps.getExposedCards());
    this.rps.removeUsingDraw(6,1, 1);
    assertNotEquals(exposed, this.rps.getExposedCards());
    exposed.clear();
    exposed.add(new Card(Symbol.Club, Value.Ace));
    exposed.add(new Card(Symbol.Club, Value.Two));
    assertEquals(exposed, this.rps.getExposedCards());
  }

  @Test
  public void updateAndExposeBPS() {
    this.resetBPS();
    bps.startGame(bps.getDeck(), false, 2, 1);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Club, Value.Two));
    exposed.add(new Card(Symbol.Club, Value.Three));
    this.bps.updateAndExpose(1, 1);
    assertNotEquals(exposed, this.bps.getExposedCards());
    exposed.remove(1);
    assertEquals(exposed, this.bps.getExposedCards());
  }

  @Test
  public void updateAndExposeTPS() {
    this.resetTPS();
    tps.startGame(tps.getDeck(), false, 2, 1);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Club, Value.Four));
    exposed.add(new Card(Symbol.Club, Value.Five));
    exposed.add(new Card(Symbol.Club, Value.Six));
    exposed.add(new Card(Symbol.Club, Value.Seven));
    assertEquals(exposed, this.tps.getExposedCards());
    this.tps.updateAndExpose(1, 1);
    assertNotEquals(exposed, this.tps.getExposedCards());
    exposed.remove(1);
    assertEquals(exposed, this.tps.getExposedCards());
  }

  @Test
  public void updateAndExposeRPS() {
    this.resetRPS();
    rps.startGame(rps.getDeck(), false, 2, 1);
    ArrayList<Card> exposed = new ArrayList<>();
    exposed.add(new Card(Symbol.Spade, Value.Two));
    exposed.add(new Card(Symbol.Spade, Value.Three));
    this.rps.updateAndExpose(1, 0);
    assertNotEquals(exposed, this.rps.getExposedCards());
    exposed.clear();
    exposed.add(new Card(Symbol.Club, Value.Ace));
    exposed.add(new Card(Symbol.Club, Value.Three));
    assertEquals(exposed, this.rps.getExposedCards());
  }

  @Test
  public void sortIntoPyramidBPS() {
    this.resetBPS();
    // startGame calls sortIntoPyramid
    this.bps.startGame(this.bps.getDeck(), false, 2, 1);
    List<List<Card>> pyramid = new ArrayList<>();
    pyramid.add(new ArrayList<>());
    pyramid.add(new ArrayList<>());
    pyramid.get(0).add(new Card(Symbol.Club, Value.Ace));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Two));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Three));
    assertEquals(pyramid, this.bps.pyramid);
  }

  @Test
  public void sortIntoPyramidTPS() {
    this.resetTPS();
    // startGame calls sortIntoPyramid
    this.tps.startGame(this.tps.getDeck(), false, 2, 1);
    List<List<Card>> pyramid = new ArrayList<>();
    pyramid.add(new ArrayList<>());
    pyramid.add(new ArrayList<>());
    pyramid.get(0).add(new Card(Symbol.Club, Value.Ace));
    pyramid.get(0).add(new Card(Symbol.Club, Value.Two));
    pyramid.get(0).add(new Card(Symbol.Club, Value.Three));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Four));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Five));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Six));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Seven));
    assertEquals(pyramid, this.tps.pyramid);
  }

  @Test
  public void sortIntoPyramidRPS() {
    this.resetRPS();
    // startGame calls sortIntoPyramid
    this.rps.startGame(this.rps.getDeck(), false, 2, 1);
    List<List<Card>> pyramid = new ArrayList<>();
    pyramid.add(new ArrayList<>());
    pyramid.add(new ArrayList<>());
    pyramid.get(0).add(new Card(Symbol.Club, Value.Ace));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Two));
    pyramid.get(1).add(new Card(Symbol.Club, Value.Three));
    assertEquals(pyramid, this.rps.pyramid);
  }

  @Test
  public void sortIntoDrawBPS() {
    this.resetBPS();
    // startGame calls sortIntoDraw
    this.bps.startGame(this.bps.getDeck(), false, 3, 1);
    this.draw = new ArrayList<>();
    this.draw.add(new Card(Symbol.Club, Value.Seven));
    assertEquals(this.draw, this.bps.getDrawCards());
  }

  @Test
  public void sortIntoDrawTPS() {
    this.resetTPS();
    // startGame calls sortIntoDraw
    this.tps.startGame(this.tps.getDeck(), false, 3, 1);
    this.draw.add(new Card(Symbol.Club, Value.King));
    assertEquals(this.draw, this.tps.getDrawCards());
  }

  @Test
  public void sortIntoDrawRPS() {
    this.resetRPS();
    // startGame calls sortIntoDraw
    this.rps.startGame(this.rps.getDeck(), false, 3, 1);
    this.draw.add(new Card(Symbol.Club, Value.Seven));
    assertEquals(this.draw, this.rps.getDrawCards());
  }
}