package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.LinkedList;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardState;
import cs3500.pyramidsolitaire.model.hw02.GameState;

/**
 * A relaxed version of BasicPyramidSolitaire where
 * cards that are half-exposed can still be removed.
 */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Instantiates a game of RelaxedPyramidSolitaire.
   */
  public RelaxedPyramidSolitaire() {
    super(GameState.NotStarted, new ArrayList<>(),
            new LinkedList<>(), 0, 0, new Card[0]);
  }

  @Override
  // can we expose any cards based on the given card being removed
  void updateAndExpose(int row, int card) {

    this.pyramid.get(row).set(card, null);

    // check left above aka (row - 1, card - 1)
    // by checking if card to c's left (row, card - 1) is removed
    // edge case: c is left-most card
    if (!invalidCoordinates(row - 1, card - 1)) {
      // if left-above is valid, then left is valid
      Card update = this.getCardAt(row - 1, card - 1);
      if (update != null) {
        update.cardState = CardState.Exposed;
      }
    }

    // check right above aka (row - 1, card)
    // by checking if card to c's right (row, card + 1) is removed
    // edge case: c is right-most card
    if (!invalidCoordinates(row - 1, card)) {
      // if right-above is valid, then right is valid
      Card update = this.getCardAt(row - 1, card);
      if (update != null) {
        update.cardState = CardState.Exposed;
      }
    }
  }
}