package cs3500.pyramidsolitaire.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Renders a textual view of the state of a PyramidSolitaire Game.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private final Appendable ap;

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
    this.ap = null;
  }

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public String toString() {
    String conc = "";
    if (this.model instanceof BasicPyramidSolitaire) {
      try {
        this.model.isGameOver();
      } catch (Exception e) {
        // do nothing.
      }
      if (this.model.getNumRows() == -1) {
        return "";
      } else if (this.model.getScore() == 0) {
        return "You win!";
      } else if (this.model.isGameOver() && this.model.getScore() > 0) {
        return "Game over. Score: " + this.model.getScore();
      } else {
        int numRows = this.model.getNumRows();

        // for each row
        for (int r = 0; r < numRows; r++) {
          int exp = (2 * (numRows - 1 - r));

          for (int i = 0; i < exp; i++) {
            conc = conc.concat(" ");
          }
          for (int w = 0; w <= r; w++) {
            Object c = this.model.getCardAt(r, w);
            if (c == null) {
              if (!isLastElement(r, w)) {
                conc = conc.concat("    ");
              }
            } else {
              if (w == r || isLastElement(r, w)) {
                conc = conc.concat(c.toString());

              } else if (((Card) c).getValue() == 10) {
                conc = conc.concat(c.toString() + " ");
              } else {
                conc = conc.concat(c.toString() + "  ");
              }
            }
          }
          conc = conc.concat("\n");
        }

        List<Object> canDraw = new ArrayList<>(this.model.getDrawCards());
        conc = conc.concat("Draw:");
        for (int d = 0; d < canDraw.size(); d++) {
          if (canDraw.get(d) != null) {
            Object draw = canDraw.get(d);
            if (d == 0) {
              conc = conc.concat(" " + draw.toString());
            } else {
              conc = conc.concat(", " + draw.toString());
            }
          }
        }
        return conc;
      }
    }
    return conc;
  }

  // renders the game state in string form to the Appendable.
  public void render() throws IOException {
    this.ap.append(this.toString() + "\n");
  }

  // checks if the given coordinates represents a card that is the
  // leftmost in its pyramid row.
  boolean isLastElement(int row, int card) {
    for (int i = card + 1; i <= row; i++) {
      Object c = this.model.getCardAt(row, i);
      if (c != null) {
        return false;
      }
    }

    return true;
  }
}
