package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Implements PyramidSolitaireController in text form, to allow user to play the game in text form.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  Readable rd;
  Appendable ap;
  PyramidSolitaireModel model;
  ArrayList<String> currMove;
  boolean gameOver;
  Scanner scan;

  /**
   * Main constructor to instantiate a PyramidSolitaireTextualController.
   *
   * @param rd input stream
   * @param ap output stream
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException();
    }

    this.rd = rd;
    this.ap = ap;
    this.model = null;
    this.currMove = new ArrayList<>();
    this.gameOver = false;
    this.scan = new Scanner(this.rd);

  }

  @Override
  public <Card> void playGame(PyramidSolitaireModel<Card> model, List<Card> deck,
                              boolean shuffle, int numRows, int numDraw) {

    this.handleNull(model);

    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (Exception e) {
      throw new IllegalStateException("Game cannot be started.");
    }

    this.model = model;

    this.whileGameNotOverLoop();
  }

  // main loop for playGame.
  void whileGameNotOverLoop() {

    // rerun everytime a move is completed
    while (!this.gameOver) {
      String s = this.readHandlingError();
      //a. Transmit game state to the Appendable object exactly
      //   as the view of the model provides it.
      this.showPyramid();

      //b. Transmit "Score: N", replacing N with the actual score.
      this.appendHandlingError("Score: " + this.model.getScore());

      //c. If the game is ongoing, obtain the next user input from the Readable object.
      if (wantsToQuit(s)) {
        this.quitGame(s);
      } else if (isNewMove(s)) {
        this.methodAllocater(s);
      } else {
        String str = waitForMove(s);
        this.methodAllocater(str);
      }

      //d. If the game is over, the method should transmit the final game state one last time.
      //   The method should then end.
      this.endIfGameOver();
    }
  }

  // checks if given string represents a new move.
  boolean isNewMove(String s) {
    return s.equals("rm1")
            || s.equals("rm2")
            || s.equals("rmwd")
            || s.equals("dd");
  }

  // RECURSIVE
  // waits for user to input a string that represents a move.
  String waitForMove(String s) {
    if (isNewMove(s) || wantsToQuit(s)) {
      return s;
    } else {
      this.appendHandlingError("Input is not a new move.");
      return waitForMove(this.readHandlingError());
    }
  }

  // RECURSIVE
  // waits for user to input a string that's either a command to quit or a number.
  String returnOnlyValidInput(String s) {
    if (wantsToQuit(s) || isNumber(s)) {
      return s;
    } else {
      this.appendHandlingError("Invalid input.");
      returnOnlyValidInput(this.readHandlingError());
    }
    return "should never reach here in a real game";
  }

  // allocates the move based on the user's command.
  void methodAllocater(String s) {
    switch (s) {
      case ("rm1"):
        handleRemoveOne();
        break;
      case ("rm2"):
        handleRemoveTwo();
        break;
      case ("rmwd"):
        handleRemoveWithDraw();
        break;
      case ("dd"):
        handleDiscardDraw();
        break;
      case ("q"):
      case ("Q"):
      case ("closed"):
        this.quitGame(s);
        break;
      default:
        break;
    }
  }

  // handles user's input for rm1.
  void handleRemoveOne() {
    ArrayList<String> temp = new ArrayList<>();
    int isFilled = 0;
    while (isFilled < 2) {
      String s = returnOnlyValidInput(this.readHandlingError());
      if (wantsToQuit(s)) {
        this.quitGame(s);
        break;
      } else {
        isFilled++;
        temp.add(s);
      }
    }

    if (isFilled == 2) {
      try {
        this.model.remove(translateInput(temp.get(0)), translateInput(temp.get(1)));
      } catch (Exception e) {
        this.appendHandlingError(e.getMessage());
      }

    }

  }

  // handles user's input for rm2.
  void handleRemoveTwo() {
    ArrayList<String> temp = new ArrayList<>();
    int isFilled = 0;
    while (isFilled < 4) {
      String s = returnOnlyValidInput(this.readHandlingError());
      if (wantsToQuit(s)) {
        this.quitGame(s);
        break;
      } else {
        isFilled++;
        temp.add(s);
      }
    }

    if (isFilled == 4) {
      try {
        this.model.remove(translateInput(temp.get(0)), translateInput(temp.get(1)),
                translateInput(temp.get(2)), translateInput(temp.get(3)));
      } catch (Exception e) {
        this.appendHandlingError(e.getMessage());
      }

    }
  }

  // handles user's input for rmwd.
  void handleRemoveWithDraw() {
    ArrayList<String> temp = new ArrayList<>();
    int isFilled = 0;
    while (isFilled < 3) {
      String s = returnOnlyValidInput(this.readHandlingError());
      if (wantsToQuit(s)) {
        this.quitGame(s);
        break;
      } else {
        isFilled++;
        temp.add(s);
      }
    }

    if (isFilled == 3) {
      try {
        this.model.removeUsingDraw(translateInput(temp.get(0)),
                translateInput(temp.get(1)), translateInput(temp.get(2)));
      } catch (Exception e) {
        this.appendHandlingError(e.getMessage());
      }

    }
  }

  // handles user's input for dd.
  void handleDiscardDraw() {
    ArrayList<String> temp = new ArrayList<>();
    int isFilled = 0;
    while (isFilled < 1) {
      String s = returnOnlyValidInput(this.readHandlingError());
      if (wantsToQuit(s)) {
        this.quitGame(s);
        break;
      } else {
        isFilled++;
        temp.add(s);
      }
    }

    if (isFilled == 1) {
      try {
        this.model.discardDraw(translateInput(temp.get(0)));
      } catch (Exception e) {
        this.appendHandlingError(e.getMessage());
      }

    }
  }

  // appends appropriate ending string to appendable.
  void quitGame(String s) {
    if (wantsToQuit(s)) {
      this.appendHandlingError("Game quit!");
      this.appendHandlingError("State of game when quit:");
      this.showPyramid();
      this.appendHandlingError("Score: " + this.model.getScore());
      this.gameOver = true;
    }
  }

  // handles the IOException that may be thrown if output can't be transmitted.
  void appendHandlingError(String s) {
    try {
      this.ap.append(s + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  // shows the state of the game as returned by the textualview.
  void showPyramid() {
    try {
      new PyramidSolitaireTextualView(this.model, this.ap).render();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // handles if a null model is given to the game.
  void handleNull(PyramidSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
  }

  // checks if the input indicates that the user wants to quit.
  boolean wantsToQuit(String s) {
    return s.equals("q") || s.equals("Q") || s.equals("closed");
  }

  // checks if the given string is a number.
  boolean isNumber(String num) {
    try {
      Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  // translates the given string into appropriate number for the model.
  int translateInput(String s) {
    return Integer.parseInt(s) - 1;
  }

  // handles the error of if the next input can't be read or if it doesn't exist.
  String readHandlingError() {
    try {
      return this.scan.next();
    } catch (Exception e) {
      return "closed";
    }
  }

  // ends the game if the game is over.
  void endIfGameOver() {
    if (this.handleIsGameOverError()) {
      this.showPyramid();
      this.gameOver = true;
    }
  }

  // handles the error that the model may throw for isGameOver.
  boolean handleIsGameOverError() {
    try {
      return this.model.isGameOver();
    } catch (Exception e) {
      return true;
    }
  }
}