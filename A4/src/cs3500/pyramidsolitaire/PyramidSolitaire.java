package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import java.io.InputStreamReader;

/**
 * The class for the entry point of the program.
 */
public class PyramidSolitaire {

  /**
   * The entry point of the program.
   *
   * @param args the input that determines the type of PyramidSolitaire game
   */
  public static void main(String[] args) {

    PyramidSolitaireTextualController pstc;
    PyramidSolitaireModel<Card> psm;
    PyramidSolitaireTextualView pstv;
    PyramidSolitaireCreator.GameType gt;
    String numRows = "";
    String numDraw = "";
    boolean inputs = true;

    String version = args[0];

    if (version.equals("basic")) {
      gt = PyramidSolitaireCreator.GameType.BASIC;
    } else if (version.equals("relaxed")) {
      gt = PyramidSolitaireCreator.GameType.RELAXED;
    } else {
      gt = PyramidSolitaireCreator.GameType.TRIPEAKS;
    }

    pstc = new PyramidSolitaireTextualController(new InputStreamReader(System.in), System.out);
    psm = new PyramidSolitaireCreator().create(gt);
    pstv = new PyramidSolitaireTextualView(psm);

    // if more arguments were passed in, they're assigned
    if (args.length == 3) {
      numRows = args[1];
      numDraw = args[2];
    }

    // if more arguments weren't passed in, set inputs as false
    if (args.length != 3) {
      inputs = false;
    }

    // see if variables are integers
    // (if no arguments were passed in, this would return false anyways)
    try {
      Integer.parseInt(numRows);
      Integer.parseInt(numDraw);
    } catch (NumberFormatException e) {
      inputs = false;
    }

    if (inputs) {
      pstc.playGame(psm, psm.getDeck(),
              false, Integer.parseInt(numRows), Integer.parseInt(numDraw));
    } else {
      pstc.playGame(psm, psm.getDeck(), false, 7, 3);
    }


  }

}
