package cs3500.pyramidsolitaire.model.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests all the non public additional methods in BasicPyramidSolitaire.
 */
public class BasicPyramidSolitaireTest {

  private BasicPyramidSolitaire bps;
  private List<Card> fullDeck;
  private List<Card> invalidDeckOne;
  private List<Card> invalidDeckTwo;
  private LinkedList<Card> stock;
  private List<List<Card>> pyramid;

  private Card[] draw;
  private List<Card> exposedCards;

  private Card c;
  private Card d9;
  private Card d10;
  private Card d11;
  private Card d12;
  private Card d13;
  private Card h1;
  private Card h2;
  private Card h3;


  private void reset() {
    this.bps = new BasicPyramidSolitaire();
    this.fullDeck = new ArrayList<>();
    this.c = new Card(Symbol.Club, Value.Jack);

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
    this.d9 = new Card(Symbol.Diamond, Value.Nine);
    this.d10 = new Card(Symbol.Diamond, Value.Ten);
    this.d11 = new Card(Symbol.Diamond, Value.Jack);
    this.d12 = new Card(Symbol.Diamond, Value.Queen);
    this.d13 = new Card(Symbol.Diamond, Value.King);

    // hearts
    this.h1 = new Card(Symbol.Heart, Value.Ace);
    this.h2 = new Card(Symbol.Heart, Value.Two);
    this.h3 = new Card(Symbol.Heart, Value.Three);
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

    this.fullDeck =
            new ArrayList<>(Arrays.asList(
                    c1, c2, c3, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, d13,
                    h1, this.h2, this.h3, h4, h5, h6,
                    h7, h8, h9, h10, h11, h12, h13,
                    s1, s2, s3, s4, s5, s6,
                    s7, s8, s9, s10, s11, s12, s13));

    this.invalidDeckOne =
            new ArrayList<>(Arrays.asList(
            c1, c1, c2, c3, c4, c5, c6,
            c7, c8, c9, c10, c11, c12, c13,
            d1, d2, d3, d4, d5, d6,
            d7, d8, d9, d10, d11, d12, d13,
            h1, this.h2, this.h3, h4, h5, h6,
            h7, h8, h9, h10, h11, h12, h13,
            s1, s2, s3, s4, s5, s6,
            s7, s8, s9, s10, s11, s12, s13));

    this.invalidDeckTwo =
            new ArrayList<>(Arrays.asList(
                    c1, c2, c3, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, d13,
                    h1, this.h2, this.h3, h4, h5));
  }

  private void drawInitializer() {
    this.reset();
    this.draw = new Card[3];
    draw[0] = this.h2;
    draw[1] = null;
    draw[2] = this.h3;
  }

  private void drawAndStockInitializer() {
    this.reset();
    this.stock = new LinkedList<>();
    this.draw = new Card[3];
    draw[0] = this.h2;
    draw[1] = this.c;
    draw[2] = this.h3;
  }

  private void initializeExposedCards() {
    this.reset();
    List<Card> adding = Arrays.asList(d9, d10, d11, d12, d13, h1, h2);
    this.exposedCards = new ArrayList<>();
    this.exposedCards.addAll(adding);
  }

  @Test
  public void testInvalidDeck() {
    this.reset();
    assertFalse(this.bps.invalidDeck(this.fullDeck));
    this.reset();
    assertTrue(this.bps.invalidDeck(this.invalidDeckOne));
    this.reset();
    assertTrue(this.bps.invalidDeck(this.invalidDeckTwo));
  }

  @Test
  public void testInvalidRowsAndDraw() {
    this.reset();
    assertFalse(this.bps.invalidRowsAndDraw(7,1));
    assertFalse(this.bps.invalidRowsAndDraw(7,0));
    assertTrue(this.bps.invalidRowsAndDraw(10, 1));
    assertTrue(this.bps.invalidRowsAndDraw(0, 3));
    assertTrue(this.bps.invalidRowsAndDraw(5, -1));
    assertTrue(this.bps.invalidRowsAndDraw(5, 53));
  }

  @Test
  public void testInvalidCard() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertTrue(this.bps.invalidCard(7, 7));
    assertTrue(this.bps.invalidCard(-1, -1));
    assertTrue(this.bps.invalidCard(-1, -1));
    assertFalse(this.bps.invalidCard(6, 6));
    assertFalse(this.bps.invalidCard(6, 2));
    this.bps.remove(6, 2, 6, 6);
    assertTrue(this.bps.invalidCard(6, 2));
  }

  @Test
  public void testInvalidCoordinates() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertTrue(this.bps.invalidCoordinates(7, 3));
    assertTrue(this.bps.invalidCoordinates(3, 4));
    assertTrue(this.bps.invalidCoordinates(4, -1));
    assertTrue(this.bps.invalidCoordinates(-1, 4));
    assertFalse(this.bps.invalidCoordinates(6, 3));
    assertFalse(this.bps.invalidCoordinates(0, 0));
  }

  @Test
  public void testInvalidCards() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.invalidCards(6,2,6,6));
    assertTrue(this.bps.invalidCards(6,2,6,1));
  }

  @Test (expected = IllegalStateException.class)
  public void testGameNotStartedErrorOne() {
    this.reset();
    this.bps.gameNotStartedError();
  }

  @Test
  public void testGameNotStartedErrorTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    // shouldn't do anything because game has been started
    this.bps.gameNotStartedError();
  }

  @Test
  public void testInvalidDrawIndex() {
    this.drawInitializer();
    this.bps = new BasicPyramidSolitaire.BPSBuilder().maxDraw(3).draw(this.draw).build();
    assertFalse(this.bps.invalidDrawIdx(0));
    assertTrue(this.bps.invalidDrawIdx(3));
    assertTrue(this.bps.invalidDrawIdx(1));
    this.drawInitializer();
    this.bps = new BasicPyramidSolitaire.BPSBuilder().maxDraw(3).draw(this.draw).build();
    assertFalse(this.bps.invalidDrawIdx(0));
    assertFalse(this.bps.invalidDrawIdx(2));
    assertTrue(this.bps.invalidDrawIdx(1));
  }

  @Test
  public void testInvalidCombo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 3);
    assertFalse(this.bps.invalidCombo(this.bps.getCardAt(6, 2),
            this.bps.getCardAt(6, 6)));
    assertTrue(this.bps.invalidCombo(this.bps.getCardAt(6, 1),
            this.bps.getCardAt(6, 6)));
    assertTrue(this.bps.invalidCombo(this.bps.getCardAt(6, 1),
            null));
    this.reset();
    this.bps.startGame(this.fullDeck, false, 5, 1);
    assertFalse(this.bps.invalidCombo(this.bps.getCardAt(4, 2), null));
  }

  @Test
  public void testUpdateDrawPile() {
    this.drawAndStockInitializer();
    this.bps = new BasicPyramidSolitaire
            .BPSBuilder()
            .gameState(GameState.Started)
            .stock(this.stock)
            .maxDraw(3)
            .draw(this.draw)
            .build();
    assertFalse(this.bps.invalidDrawIdx(0));
    this.bps.updateDrawPile(0);
    System.out.println(this.bps.getDrawCards());
    assertTrue(this.bps.invalidDrawIdx(0));
  }

  @Test
  public void testGameWon() {
    this.reset();
    this.bps = new BasicPyramidSolitaire.BPSBuilder().gameState(GameState.Won).build();
    assertTrue(this.bps.gameWon());
    this.reset();
    this.bps = new BasicPyramidSolitaire
            .BPSBuilder()
            .pyramid(new ArrayList<>())
            .gameState(GameState.Started)
            .build();
    assertTrue(this.bps.gameWon());
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.gameWon());
  }

  @Test
  public void testGameLost() {
    this.reset();
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.fullDeck, false, 1, 0);
    assertTrue(this.bps.gameLost());
  }

  @Test
  public void testGetExposedCards() {
    this.initializeExposedCards();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertEquals(this.bps.getExposedCards(), this.exposedCards);
  }

  @Test
  public void testCantRemoveOne() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 1, 1);
    assertTrue(this.bps.cantRemoveOne());
    this.bps.startGame(this.fullDeck, false, 5, 1);
    assertFalse(this.bps.cantRemoveOne());
  }

  @Test
  public void testCantRemoveTwo() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.cantRemoveTwo());
    this.bps.startGame(this.fullDeck, false, 1, 1);
    assertTrue(this.bps.cantRemoveTwo());
  }

  @Test
  public void testCantRemoveUsingDraw() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.cantRemoveUsingDraw());
    this.bps.startGame(this.fullDeck, false, 1, 1);
    assertTrue(this.bps.cantRemoveUsingDraw());
    this.bps.startGame(this.fullDeck, false, 7, 0);
    assertTrue(this.bps.cantRemoveUsingDraw());
  }

  @Test
  public void testNoMoreDrawCards() {
    this.reset();
    this.bps = new BasicPyramidSolitaire.BPSBuilder().maxDraw(3).draw(new Card[3]).build();
    assertTrue(this.bps.noMoreDrawCards());
    this.pyramid = new ArrayList<>();
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertFalse(this.bps.noMoreDrawCards());
  }

  @Test
  public void testUpdateAndExpose() {
    this.reset();
    this.bps.startGame(this.fullDeck, false, 7, 1);
    assertTrue(this.bps.invalidCard(5,5));
    this.bps.updateAndExpose(6, 6);
    this.bps.updateAndExpose(6, 5);
    assertFalse(this.bps.invalidCard(5,5));
  }

  @Test
  public void testSortIntoPyramid() {
    this.reset();
    this.pyramid = new ArrayList<>();
    List<List<Card>> bpsPyramid = new ArrayList<>();
    this.stock = new LinkedList<>();
    this.stock.add(this.d9);
    this.stock.add(this.d10);
    this.stock.add(this.d11);
    this.bps = new BasicPyramidSolitaire
            .BPSBuilder()
            .maxRows(2)
            .pyramid(bpsPyramid)
            .stock(this.stock)
            .build();
    this.bps.sortIntoPyramid();
    this.pyramid.add(new ArrayList<>());
    this.pyramid.add(new ArrayList<>());
    this.pyramid.get(0).add(this.d9);
    this.pyramid.get(1).add(this.d10);
    this.pyramid.get(1).add(this.d11);
    assertEquals(bpsPyramid, this.pyramid);
  }

  @Test
  public void testSortIntoDraw() {
    this.reset();
    ArrayList<Card> drawCards = new ArrayList<>();
    drawCards.add(this.c);
    this.stock = new LinkedList<>();
    this.stock.add(this.c);
    this.bps = new BasicPyramidSolitaire
            .BPSBuilder()
            .maxDraw(1)
            .stock(this.stock)
            .draw(this.draw)
            .build();
    this.bps.sortIntoDraw();
    assertEquals(this.bps.getDrawCards(), drawCards);
  }
}