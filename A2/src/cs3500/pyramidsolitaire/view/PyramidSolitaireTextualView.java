package cs3500.pyramidsolitaire.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.CardState;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Value;

/**
 * Renders a textual view of the state of a PyramidSolitaire Game.
 */
public class PyramidSolitaireTextualView {
  private final PyramidSolitaireModel<?> model;
  // ... any other fields you need

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  @Override
  public String toString() {
    if (this.model.getNumRows() == -1) {
      return "";
    } else if (this.model.getScore() == 0) {
      return "You win!";
    } else if (this.model.isGameOver()) {
      return "Game over. Score: " + this.model.getScore();
    } else {
      int numRows = this.model.getNumRows();
      String conc = "";

      // for each row
      for (int r = 0; r < numRows; r++) {
        int exp = (2 * (numRows - 1 - r));

        for (int i = 0; i < exp; i++) {
          conc = conc.concat(" ");
        }
        for (int w = 0; w <= r; w++) {
          Object c = this.model.getCardAt(r, w);
          if (c instanceof Card) {
            if (((Card) c).returnState() == CardState.Removed) {
              conc = conc.concat("    ");
            } else {
              if (w == r) {
                conc = conc.concat(c.toString());

              } else if (((Card) c).getValue() == Value.Ten) {
                conc = conc.concat(c.toString() + " ");
              } else {
                conc = conc.concat(c.toString() + "  ");
              }
            }
          }
        }

        conc = conc.concat("\n");

      }

      List<Object> canDraw = new ArrayList<>(this.model.getDrawCards());
      conc = conc.concat("Draw:");
      for (int d = 0; d < canDraw.size(); d++) {
        Object draw = canDraw.get(d);
        if (d == 0) {
          conc = conc.concat(" " + draw.toString());
        } else {
          conc = conc.concat(", " + draw.toString());
        }
      }

      return conc;
    }
  }
}