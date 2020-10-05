package cs3500.pyramidsolitaire.controller;

import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests that all the non public methods in PyramidSolitaireTextualController works properly.
 */
public class PyramidSolitaireTextualControllerTest {

  PyramidSolitaireTextualController pstController;
  Reader in;
  StringBuilder out;

  private BasicPyramidSolitaire bps;

  String gameRendered =
          "            A♣\n          2♣  3♣\n        4♣  5♣  6♣\n      7♣  8♣  9♣  10♣\n"
                  + "    J♣  Q♣  K♣  A♦  2♦\n  3♦  4♦  5♦  6♦  7♦  8♦\n"
                  + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\nDraw: 3♥\nScore: 185\n"
                  + "Game quit!\nState of game when quit:\n            A♣\n"
                  + "          2♣  3♣\n        4♣  5♣  6♣\n      7♣  8♣  9♣  10♣\n"
                  + "    J♣  Q♣  K♣  A♦  2♦\n  3♦  4♦  5♦  6♦  7♦  8♦\n"
                  + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\nDraw: 3♥\nScore: 185\n";

  private void initializeController() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("stuff");
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.pstController = new PyramidSolitaireTextualController(in, out);
  }

  @Test
  public void testWhileGameNotOverLoopOne() {
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.bps.getDeck(), false, 1, 0);
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.model = this.bps;
    this.pstController.whileGameNotOverLoop();
    assertEquals("Game over. Score: 1\n"
            + "Score: 1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "Game over. Score: 1\n"
            + "Score: 1\n"
            + "Game over. Score: 1\n", out.toString());
  }

  @Test
  public void testWhileGameNotOverLoopTwo() {
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.model = this.bps;
    this.pstController.whileGameNotOverLoop();
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦      A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦      A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n", out.toString());
  }

  @Test
  public void testWhileGameNotOverLoopThree() {
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.model = this.bps;
    this.pstController.whileGameNotOverLoop();
    assertEquals(this.gameRendered, out.toString());
  }

  @Test
  public void testIsNewMove() {
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    assertFalse(this.pstController.isNewMove("rm 1"));
    assertFalse(this.pstController.isNewMove("q"));
    assertTrue(this.pstController.isNewMove("rm1"));
  }

  @Test
  public void testWaitForMove() {
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    assertEquals(this.pstController.waitForMove("rm1"), "rm1");
    assertEquals(this.pstController.waitForMove("q"), "q");
    this.pstController.waitForMove("0");
    assertEquals("Input is not a new move.\n", out.toString());
  }

  @Test
  public void testReturnOnlyValidInput() {
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 q 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    assertEquals(this.pstController.returnOnlyValidInput("rm1"),
            "should never reach here in a real game");
    assertEquals(this.pstController.returnOnlyValidInput("q"), "q");
    this.pstController.waitForMove("0");
    assertEquals("Invalid input.\n" +
            "Invalid input.\n" +
            "Input is not a new move.\n", out.toString());
  }

  @Test
  public void testMethodAllocater() {
    this.out = new StringBuilder();
    this.in = new StringReader("7 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    assertEquals("", out.toString());
    this.pstController.methodAllocater("rm1");
    assertEquals("null\n", out.toString());
  }

  @Test
  public void testHandleRemoveOne() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    // playGame should call handleRemoveOne
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦      A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦      A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n", out.toString());
  }

  @Test
  public void testHandleRemoveTwo() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rm2 7 3 7 7");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    // playGame should call handleRemoveTwo
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦     Q♦  K♦  A♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦     Q♦  K♦  A♥\n"
            + "Draw: 3♥\n"
            + "Score: 172\n", out.toString());
  }

  @Test
  public void testHandleRemoveWithDraw() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("rmwd 1 7 2");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    // playGame should call handleRemoveWithDraw
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦      J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 4♥\n"
            + "Score: 175\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦      J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 4♥\n"
            + "Score: 175\n", out.toString());
  }

  @Test
  public void testHandleDiscardDraw() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("dd 1");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    // playGame should call handleDiscardDraw
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 4♥\n"
            + "Score: 185\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 4♥\n"
            + "Score: 185\n", out.toString());
  }

  @Test
  public void testQuitGame() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("q");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    // playGame should call quitGame
    pstController.playGame(bps, bps.getDeck(), false, 7, 1);
    assertEquals(this.gameRendered, out.toString());
  }

  @Test
  public void testAppendHandlingError() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("appended.");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.appendHandlingError("appended.");
    assertEquals("appended." + "\n", out.toString());
  }

  @Test
  public void testShowPyramid() {
    this.initializeController();
    this.pstController.playGame(this.bps, this.bps.getDeck(),
            false, 7, 1);
    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n"
            + "Input is not a new move.\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥\n"
            + "Score: 185\n", out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleNull() {
    this.initializeController();
    // playGame calls handleNull
    this.pstController.playGame(null, this.bps.getDeck(),
            false, 7, 1);
  }

  @Test
  public void testWantsToQuit() {
    this.initializeController();
    assertFalse(this.pstController.wantsToQuit("0"));
    assertTrue(this.pstController.wantsToQuit("q"));
    assertTrue(this.pstController.wantsToQuit("Q"));
    assertTrue(this.pstController.wantsToQuit("closed"));
  }

  @Test
  public void testIsNumber() {
    this.initializeController();
    assertFalse(this.pstController.isNumber("q"));
    assertFalse(this.pstController.isNumber("rm1"));
    assertTrue(this.pstController.isNumber("7"));
    assertTrue(this.pstController.isNumber("1"));
  }

  @Test
  public void testTranslateInput() {
    this.initializeController();
    assertEquals(6, this.pstController.translateInput("7"));
  }

  @Test
  public void testReadHandlingError() {
    this.initializeController();
    assertEquals("stuff", this.pstController.readHandlingError());
    assertEquals("closed", this.pstController.readHandlingError());
  }

  @Test
  public void testEndIfGameOver() {
    this.bps = new BasicPyramidSolitaire();
    this.out = new StringBuilder();
    this.in = new StringReader("q");
    this.bps.startGame(this.bps.getDeck(), false, 7, 1);
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.endIfGameOver();
    assertEquals("\n", out.toString());
  }

  @Test
  public void testHandleIsGameOverError() {
    this.bps = new BasicPyramidSolitaire();
    this.bps.startGame(this.bps.getDeck(), false, 1, 0);
    this.out = new StringBuilder();
    this.in = new StringReader("rm1 7 5");
    this.pstController = new PyramidSolitaireTextualController(in, out);
    assertTrue(this.pstController.handleIsGameOverError());
    this.initializeController();
    this.pstController = new PyramidSolitaireTextualController(in, out);
    this.pstController.playGame(this.bps, this.bps.getDeck(),
            false, 7, 1);
    assertFalse(this.pstController.handleIsGameOverError());
  }

}
