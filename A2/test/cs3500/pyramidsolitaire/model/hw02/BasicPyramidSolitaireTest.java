package cs3500.pyramidsolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests all the non public additional methods in BasicPyramidSolitaire.
 */
public class BasicPyramidSolitaireTest {

  BasicPyramidSolitaire bps;
  private List<Card> deck;
  Card c;
  Card h2;
  Card h3;

  private void reset() {
    this.bps = new BasicPyramidSolitaire();
    this.deck = new ArrayList<>();
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
    Card d9 = new Card(Symbol.Diamond, Value.Nine);
    Card d10 = new Card(Symbol.Diamond, Value.Ten);
    Card d11 = new Card(Symbol.Diamond, Value.Jack);
    Card d12 = new Card(Symbol.Diamond, Value.Queen);
    Card d13 = new Card(Symbol.Diamond, Value.King);

    // hearts
    Card h1 = new Card(Symbol.Heart, Value.Ace);
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

    this.deck =
            new ArrayList<>(Arrays.asList(
                    c1, c2, c3, c4, c5, c6,
                    c7, c8, c9, c10, c11, c12, c13,
                    d1, d2, d3, d4, d5, d6,
                    d7, d8, d9, d10, d11, d12, d13,
                    h1, this.h2, this.h3, h4, h5, h6,
                    h7, h8, h9, h10, h11, h12, h13,
                    s1, s2, s3, s4, s5, s6,
                    s7, s8, s9, s10, s11, s12, s13));
  }

  @Test
  public void gameNotStarted() {
    this.reset();
    assertTrue(this.bps.gameNotStarted());
    this.bps.startGame(this.deck, false, 7, 1);
    assertFalse(this.bps.gameNotStarted());
  }

  @Test
  public void validCoordinates() {
    this.reset();
    assertFalse(this.bps.validCoordinates(3, 4));
    this.bps.startGame(this.deck, false, 7, 1);
    assertFalse(this.bps.validCoordinates(3, 4));
    assertFalse(this.bps.validCoordinates(9, 4));
    assertFalse(this.bps.validCoordinates(-1, 4));
    assertFalse(this.bps.validCoordinates(3, -4));
    assertTrue(this.bps.validCoordinates(0, 0));
    assertTrue(this.bps.validCoordinates(6, 6));
    assertTrue(this.bps.validCoordinates(5, 2));
  }

  @Test
  public void invalidCardState() {
    this.reset();
    assertTrue(this.bps.invalidCardState(this.c));
    this.c.cardState = CardState.NotExposed;
    assertTrue(this.bps.invalidCardState(this.c));
    this.c.cardState = CardState.Removed;
    assertTrue(this.bps.invalidCardState(this.c));
    this.c.cardState = CardState.Exposed;
    assertFalse(this.bps.invalidCardState(this.c));
  }

  @Test
  public void expose() {
    this.reset();
    this.bps.startGame(this.deck, false, 7, 1);
    // removeUsingDraw calls expose
    this.bps.removeUsingDraw(0, 6, 1);
    assertNotEquals(this.h3, this.bps.getDrawCards().get(0));
  }
}