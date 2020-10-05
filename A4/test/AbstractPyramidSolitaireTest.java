import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.AbstractPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TripeaksPyramidSolitaire;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Tests for public method implementations of PyramidSolitaireModel.
 * All three implementations ought to pass the same tests.
 * We use the factory method pattern to allow the abstract base class to
 * define the tests once, and then each three subclasses overrides the factory
 * method to return an instance of the class to test.
 */
public abstract class AbstractPyramidSolitaireTest {
  protected abstract AbstractPyramidSolitaire factory();

  // todo: diff outputs.
  @Test
  public void getDeck() {
    AbstractPyramidSolitaire aps = factory();
  }

  // deck is null
  @Test(expected = IllegalArgumentException.class)
  public void startGame1() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(null, false, 7, 3);
  }

  // numRows too large
  @Test(expected = IllegalArgumentException.class)
  public void startGame2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 12, 3);
  }

  // numRows too small
  @Test(expected = IllegalArgumentException.class)
  public void startGame3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, -1, 3);
  }

  // numDraw too large
  @Test(expected = IllegalArgumentException.class)
  public void startGame4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 55);
  }

  // numDraw too small
  @Test(expected = IllegalArgumentException.class)
  public void startGame5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 5, -1);
  }

  // everything is valid & shuffled
  @Test
  public void startGame6() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), true, 7, 3);
    assertEquals(7, aps.getNumRows());
    assertEquals(3, aps.getNumDraw());
    assertFalse(aps.isGameOver());
  }

  // everything is valid & not shuffled
  @Test
  public void startGame7() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertEquals(7, aps.getNumRows());
    assertEquals(3, aps.getNumDraw());
    assertFalse(aps.isGameOver());
  }

  // game hasn't started
  @Test(expected = IllegalStateException.class)
  public void removeTwo1() {
    AbstractPyramidSolitaire aps = factory();
    aps.remove(7, 3, 7, 1);
  }

  // invalid move
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(6, 1, 6, 6);
  }

  // not-allowed card coordinates
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(5, 1, 5, 3);
  }

  // negative card coordinate
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(5, -1, 5, 3);
  }

  // negative row coordinate
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(-1, 1, 5, 3);
  }


  // card coordinate too high
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo6() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(5, 50, 5, 3);
  }

  // row coordinate too high
  @Test(expected = IllegalArgumentException.class)
  public void removeTwo7() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(10, 1, 5, 3);
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void removeOne1() {
    AbstractPyramidSolitaire aps = factory();
    aps.remove(7, 1);
  }

  // invalid move
  @Test(expected = IllegalArgumentException.class)
  public void removeOne2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(6, 3);
  }

  // invalid card coordinate (too high)
  @Test(expected = IllegalArgumentException.class)
  public void removeOne3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(6, 40);
  }

  // invalid card coordinate (too low)
  @Test(expected = IllegalArgumentException.class)
  public void removeOne4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(6, -1);
  }

  // invalid row coordinate (too low)
  @Test(expected = IllegalArgumentException.class)
  public void removeOne5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(-1, 3);
  }

  // invalid row coordinate (too high)
  @Test(expected = IllegalArgumentException.class)
  public void removeOne6() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.remove(10, 3);
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void removeUsingDraw1() {
    AbstractPyramidSolitaire aps = factory();
    aps.removeUsingDraw(0, 6, 4);
  }

  // invalid card combo (cards don't add up to 13)
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, 6, 4);
  }

  // invalid card combo (pyramid card coordinates are invalid)
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, 1, 6);
  }

  // negative drawIdx
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(-1,6, 4);
  }

  // drawIdx too high
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(4, 6, 4);
  }

  // negative row coordinate
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw6() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, -1, 4);
  }

  // negative card coordinate
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw7() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, 6, -5);
  }

  // card coordinate too high
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw8() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, 6, 30);
  }

  // row coordinate too high
  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDraw9() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.removeUsingDraw(0, 10, 4);
  }

  // game hasn't been started
  @Test(expected = IllegalStateException.class)
  public void discardDraw1() {
    AbstractPyramidSolitaire aps = factory();
    aps.discardDraw(0);
  }

  // negative drawIdx
  @Test(expected = IllegalArgumentException.class)
  public void discardDraw2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.discardDraw(-1);
  }

  // drawIdx too high
  @Test(expected = IllegalArgumentException.class)
  public void discardDraw3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.discardDraw(2);
  }

  @Test
  public void getNumRows() {
    // game hasn't been started (output: -1)
    AbstractPyramidSolitaire aps = factory();
    assertEquals(-1, aps.getNumRows());
    // # of rows originally in pyramid
    aps.startGame(aps.getDeck(), false, 2, 1);
    assertEquals(2, aps.getNumRows());
  }

  @Test
  public void getNumDraw() {
    // game hasn't been started (output: -1)
    AbstractPyramidSolitaire aps = factory();
    assertEquals(-1, aps.getNumDraw());
    // max # of visible cards in the draw pile
    aps.startGame(aps.getDeck(), false, 7, 1);
    assertEquals(1, aps.getNumDraw());
    // after discarding a card from the draw pile
    aps.discardDraw(0);
    assertEquals(1, aps.getNumDraw());
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void getRowWidth1() {
    AbstractPyramidSolitaire aps = factory();
    aps.getRowWidth(0);
  }

  // row idx is too high
  @Test(expected = IllegalArgumentException.class)
  public void getRowWidth2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.getRowWidth(8);
  }

  // row idx is too low (negative)
  @Test(expected = IllegalArgumentException.class)
  public void getRowWidth3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 1);
    aps.getRowWidth(-1);
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void isGameOver1() {
    AbstractPyramidSolitaire aps = factory();
    aps.isGameOver();
  }

  // game is over (lost)
  @Test
  public void isGameOver3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 1, 0);
    assertTrue(aps.isGameOver());
  }

  // game is not over (empty draw)
  @Test
  public void isGameOver4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 0);
    assertFalse(aps.isGameOver());
  }

  // game is not over
  @Test
  public void isGameOver5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    assertFalse(aps.isGameOver());
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void getScore1() {
    AbstractPyramidSolitaire aps = factory();
    aps.getScore();
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void getCardAt1() {
    AbstractPyramidSolitaire aps = factory();
    aps.getCardAt(0,0);
  }

  // row input too high
  @Test(expected = IllegalArgumentException.class)
  public void getCardAt2() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.getCardAt(9,0);
  }

  // row input negative
  @Test(expected = IllegalArgumentException.class)
  public void getCardAt3() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.getCardAt(-1,0);
  }

  // card input too high
  @Test(expected = IllegalArgumentException.class)
  public void getCardAt4() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.getCardAt(3,50);
  }

  // card input negative
  @Test(expected = IllegalArgumentException.class)
  public void getCardAt5() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.getCardAt(0,-1);
  }

  // illegal row, card combination (aka illegal coordinates)
  @Test(expected = IllegalArgumentException.class)
  public void getCardAt6() {
    AbstractPyramidSolitaire aps = factory();
    aps.startGame(aps.getDeck(), false, 7, 3);
    aps.getCardAt(1,9);
  }

  // game not started
  @Test(expected = IllegalStateException.class)
  public void getDrawCards1() {
    AbstractPyramidSolitaire aps = factory();
    aps.getDrawCards();
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