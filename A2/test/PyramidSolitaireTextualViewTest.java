import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

/**
 * Tests methods in the PyramidSolitaireTextualView class work properly.
 */
public class PyramidSolitaireTextualViewTest {

  private String gameRendered =
          "            A♣\n"
                  + "          2♣  3♣\n"
                  + "        4♣  5♣  6♣\n"
                  + "      7♣  8♣  9♣  10♣\n"
                  + "    J♣  Q♣  K♣  A♦  2♦\n"
                  + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                  + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
                  + "Draw: 3♥";

  private BasicPyramidSolitaire bps;
  private PyramidSolitaireTextualView view;
  private ArrayList<Card> deck;

  private void initialize() {
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

    this.bps = new BasicPyramidSolitaire();
  }

  @Test
  public void testToStringOne() {
    this.initialize();
    this.view = new PyramidSolitaireTextualView(this.bps);
    assertEquals("", this.view.toString());
    this.initialize();
    this.bps.startGame(this.deck, false, 7, 1);
    this.view = new PyramidSolitaireTextualView(this.bps);
    assertEquals(this.gameRendered, this.view.toString());
  }


  @Test
  public void testToStringTwo() {
    this.initialize();
    this.bps.startGame(this.deck, false, 7, 1);
    this.view = new PyramidSolitaireTextualView(this.bps);
    assertEquals(this.gameRendered, view.toString());
  }

  @Test
  public void testToStringThree() {
    this.initialize();
    this.bps.startGame(this.deck, false, 7, 1);
    this.view = new PyramidSolitaireTextualView(this.bps);
    assertEquals(this.gameRendered, this.view.toString());

    this.bps.removeUsingDraw(0, 6, 1);
    assertEquals(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  9♣  10♣\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦      J♦  Q♦  K♦  A♥  2♥\n"
                    + "Draw: 4♥", this.view.toString());

    this.bps.remove(6, 2, 6, 6);
    assertEquals(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  9♣  10♣\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦          Q♦  K♦  A♥      \n"
                    + "Draw: 4♥", this.view.toString());

    this.bps.remove(6, 0, 5, 1);
    assertEquals(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  9♣  10♣\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦      5♦  6♦  7♦  8♦\n"
                    + "            Q♦  K♦  A♥      \n"
                    + "Draw: 4♥", this.view.toString());

  }
}