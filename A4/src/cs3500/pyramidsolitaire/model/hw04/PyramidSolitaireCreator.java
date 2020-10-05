package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * Class for creating the appropriate PyramidSolitaire game.
 */
public class PyramidSolitaireCreator {

  /**
   * The versions of PyramidSolitaireModel that can be played.
   */
  public enum GameType { BASIC, RELAXED, TRIPEAKS }

  /**
   * Creates the appropriate PyramidSolitaireModel game based on the input.
   * @param type   the type of game to create.
   * @return       the appropriate PyramidSolitaireModel based on the type.
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    PyramidSolitaireModel<Card> psm;
    switch (type) {
      case BASIC:
        psm = new BasicPyramidSolitaire();
        break;
      case RELAXED :
        psm = new RelaxedPyramidSolitaire();
        break;
      case TRIPEAKS :
        psm = new TripeaksPyramidSolitaire();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }

    return psm;
  }

}
