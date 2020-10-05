package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.LinkedList;

import cs3500.pyramidsolitaire.model.hw04.AbstractPyramidSolitaire;

/**
 * Represents a basic implementation of the game PyramidSolitaire.
 */
public class BasicPyramidSolitaire extends AbstractPyramidSolitaire {



  /**
   * Instantiates a game of BasicPyramidSolitaire.
   */
  public BasicPyramidSolitaire() {
    super(GameState.NotStarted, new ArrayList<>(),
            new LinkedList<>(), 0, 0, new Card[0]);
  }
}
