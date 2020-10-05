import org.junit.Test;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing methods inside PyramidSolitaireTextualController.
 */
public class PyramidSolitaireTextualControllerTest {
  PyramidSolitaireTextualController pstController;
  BasicPyramidSolitaire bps;
  Reader in;
  StringBuilder out;

  String gameRendered =
          "            A♣\n          2♣  3♣\n        4♣  5♣  6♣\n      7♣  8♣  9♣  10♣\n"
                  + "    J♣  Q♣  K♣  A♦  2♦\n  3♦  4♦  5♦  6♦  7♦  8♦\n"
                  + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\nDraw: 3♥\nScore: 185\n"
                  + "Game quit!\nState of game when quit:\n            A♣\n"
                  + "          2♣  3♣\n        4♣  5♣  6♣\n      7♣  8♣  9♣  10♣\n"
                  + "    J♣  Q♣  K♣  A♦  2♦\n  3♦  4♦  5♦  6♦  7♦  8♦\n"
                  + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\nDraw: 3♥\nScore: 185\n";


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOne() {
    this.bps = new BasicPyramidSolitaire();
    this.out = null;
    this.in = new StringReader("rm1 7 q 5");
    this.pstController
            = new PyramidSolitaireTextualController(in, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTwo() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = null;
    this.pstController
            = new PyramidSolitaireTextualController(in, out);
  }

  @Test
  public void playGameOne() throws Exception {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController
            = new PyramidSolitaireTextualController(in, out);
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals(this.gameRendered, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void playGameTwo() throws IOException {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController
            = new PyramidSolitaireTextualController(in, out);
    pstController.playGame(null, bps.getDeck(), false, 7, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void playGameThree() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    pstController.playGame(this.bps, new ArrayList<>(), false, 7, 1);
  }
}