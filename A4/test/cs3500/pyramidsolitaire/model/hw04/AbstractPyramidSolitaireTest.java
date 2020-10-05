package cs3500.pyramidsolitaire.model.hw04;

import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for non-public method implementations of PyramidSolitaireModel.
 * All three implementations ought to pass the same tests.
 * We use the factory method pattern to allow the abstract base class to
 * define the tests once, and then each three subclasses overrides the factory
 * method to return an instance of the class to test.
 */
public abstract class AbstractPyramidSolitaireTest {
  protected abstract AbstractPyramidSolitaire factory();

  @Test
  public void makeDeck() {
    AbstractPyramidSolitaire aps = factory();
    assertEquals(52, aps.makeDeck().size());
  }

  @Test
  public void invalidRowsAndDraw() {
    AbstractPyramidSolitaire aps = factory();

    // numRows > 9
    assertTrue(aps.invalidRowsAndDraw(10, 3));
    // numRows < 0
    assertTrue(aps.invalidRowsAndDraw(-1, 3));
    // numRows = 0
    assertTrue(aps.invalidRowsAndDraw(0, 3));
    // numDraw < 0
    assertTrue(aps.invalidRowsAndDraw(9, -1));

    // valid input
    assertFalse(aps.invalidRowsAndDraw(7, 3));
  }

  @Test
  public void invalidCard() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    // row >= this.maxRows
    assertTrue(aps.invalidCard(8, 1));
    // card < 0
    assertTrue(aps.invalidCard(3, -1));
    // row < 0
    assertTrue(aps.invalidCard(-1, 1));
    // card is not exposed
    System.out.println(aps.getCardAt(1, 1).cardState);
    assertTrue(aps.invalidCard(1, 1));

    // valid input
    assertFalse(aps.invalidCard(6, 1));

  }

  @Test
  public void invalidCoordinates() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    // row >= this.maxRows
    assertTrue(aps.invalidCoordinates(8, 1));
    // card < 0
    assertTrue(aps.invalidCoordinates(3, -1));
    // row < 0
    assertTrue(aps.invalidCoordinates(-1, 1));

    // valid input
    assertFalse(aps.invalidCoordinates(6, 1));

  }

  @Test
  public void invalidCards() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    // row 1 too low
    assertTrue(aps.invalidCards(-1, 3, 4, 3));
    // row 1 too high
    assertTrue(aps.invalidCards(9, 3, 4, 3));
    // row 2 too low
    assertTrue(aps.invalidCards(4, 3, -1, 3));
    // row 2 too high
    assertTrue(aps.invalidCards(4, 3, 15, 3));
    // card 1 too low
    assertTrue(aps.invalidCards(4, -1, 4, 3));
    // card 1 too high
    assertTrue(aps.invalidCards(4, 30, 4, 3));
    // card 2 too low
    assertTrue(aps.invalidCards(4, 3, 4, -1));
    // card 2 too high
    assertTrue(aps.invalidCards(4, 3, 4, 30));

  }

  @Test(expected = IllegalStateException.class)
  public void gameNotStartedError1() {
    AbstractPyramidSolitaire aps = factory();
    aps.gameNotStartedError();
  }

  @Test
  public void gameNotStartedError2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.gameNotStartedError(); // should do nothing
  }

  @Test
  public void invalidDrawIdx() {
    AbstractPyramidSolitaire aps = factory();
    // draw idx is 0
    aps.startGame(aps.getDeck(), false, 7, 0);
    assertTrue(aps.invalidDrawIdx(0));
    // draw idx too high
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertTrue(aps.invalidDrawIdx(3));
    // draw idx is negative
    assertTrue(aps.invalidDrawIdx(-1));


    // valid cards
    assertFalse(aps.invalidDrawIdx(1));

  }

  @Test
  public void invalidCombo() {
    AbstractPyramidSolitaire aps = factory();
    // card 1 & 2 are null
    assertTrue(aps.invalidCombo(null, null));
    // card 1 is null
    assertTrue(aps.invalidCombo(null, new Card(Symbol.Heart, Value.King)));
    // card 2 is null & card 1 != 13
    assertTrue(aps.invalidCombo(new Card(Symbol.Heart, Value.Ace), null));
    // card 1 & 2 don't equal 13
    assertTrue(aps.invalidCombo(new Card(Symbol.Heart, Value.Ten),
            new Card(Symbol.Heart, Value.Ace)));

    // valid combo
    assertFalse(aps.invalidCombo(new Card(Symbol.Heart, Value.Ten),
            new Card(Symbol.Heart, Value.Three)));
  }

  @Test
  public void gameWon() {
    // todo
  }

  @Test
  public void gameLost() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 1, 0);
    assertTrue(aps.isGameOver());
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertFalse(aps.isGameOver());

  }

  @Test
  public void getExposedCards() {
    // todo
  }

  @Test
  public void cantRemoveOne() {
    AbstractPyramidSolitaire aps = factory();
    // false
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertFalse(aps.cantRemoveOne());
    // true
    aps.startGame(aps.getDeck(), false, 1, 3);
    assertTrue(aps.cantRemoveOne());

  }

  @Test
  public void cantRemoveTwo() {
    AbstractPyramidSolitaire aps = factory();
    // false
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertFalse(aps.cantRemoveTwo());
    // true
    aps.startGame(aps.getDeck(), false, 1, 3);
    assertTrue(aps.cantRemoveTwo());

  }

  @Test
  public void cantRemoveUsingDraw() {
    AbstractPyramidSolitaire aps = factory();
    // false
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertFalse(aps.cantRemoveUsingDraw());
    // true
    aps.startGame(aps.getDeck(), false, 1, 0);
    assertTrue(aps.cantRemoveUsingDraw());
    aps.startGame(aps.getDeck(), false, 1, 1);
    assertTrue(aps.cantRemoveUsingDraw());

  }

  @Test
  public void noMoreDrawCards() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    // false
    assertFalse(aps.noMoreDrawCards());
    // true
    aps.startGame(aps.getDeck(), false, 7, 0);
    assertTrue(aps.noMoreDrawCards());
  }

  /**
   * Constructs a BasicPyramidSolitaire instance of AbstractPyramidSolitaire.
   */
  public static final class BPSTest extends AbstractPyramidSolitaireTest {
    protected AbstractPyramidSolitaire factory() {
      return new BasicPyramidSolitaire();
    }
  }

  /**
   * Constructs a RelaxedPyramidSolitaire instance of AbstractPyramidSolitaire.
   */
  public static final class RPSTest extends AbstractPyramidSolitaireTest {
    protected AbstractPyramidSolitaire factory() {
      return new RelaxedPyramidSolitaire();
    }
  }

  /**
   * Constructs a TripeaksPyramidSolitaire instance of AbstractPyramidSolitaire.
   */
  public static final class TPSTest extends AbstractPyramidSolitaireTest {
    protected AbstractPyramidSolitaire factory() {
      return new TripeaksPyramidSolitaire();
    }
  }
}