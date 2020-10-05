package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.AValue;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.ConcatString;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.model.Product;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * Evaluates the given Formula.
 */
public class FormulaEvaluator implements SexpVisitor<AValue> {
  private Worksheet w;
  private Coord c;

  /**
   * Creates an instance of FormulaEvaluator.
   *
   * @param w is the type of Worksheet.
   * @param c is the Coord of the Cell with the formula in it.
   */
  public FormulaEvaluator(Worksheet w, Coord c) {
    this.w = w;
    this.c = c;
  }

  @Override
  public AValue visitBoolean(boolean b) {
    return new BooleanValue(b);
  }

  @Override
  public AValue visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public AValue visitSymbol(String s) {
    if (w.isReference(s)) {
      Coord coordinate = w.createCoord(s);
      return w.getContents(coordinate).evaluate(this.w, coordinate);
    } else if (w.isRange(s)) {
      return w.getContents(w.createCoord(s)).evaluate(this.w, this.c);
    } else {
      switch (s) {
        case ("SUM"):
        case ("PRODUCT"):
        case ("<"):
        case ("CONCAT"):
          // TODO: THROW AN ERROR!! (invalid # of inputs)
        default:
          return new StringValue(s);
      }
    }
  }

  @Override
  public AValue visitString(String s) {
    return new StringValue(s);
  }

  @Override
  public AValue visitSList(List<Sexp> list) {
    ArrayList<Sexp> l = new ArrayList<>();
    l.addAll(list);
    if (l.size() == 0) {
      return new StringValue("");
    } else if (l.size() == 1) {
      return (l.get(0)).accept(new FormulaEvaluator(this.w, this.c));
    } else {
      String s = l.get(0).toString();
      switch (s) {
        case ("SUM"):
          l.remove(0);
          return new Sum(this.w, this.c, l).evaluate();
        case ("PRODUCT"):
          l.remove(0);
          return new Product(this.w, this.c, l).evaluate();
        case ("CONCAT"):
          l.remove(0);
          return new ConcatString(this.w, this.c, l).evaluate();

        case ("<"):
          if (l.size() == 3) {
            l.remove(0);
            return new LessThan(this.w, this.c, l.get(0), l.get(1)).evaluate();
          } else {
            return new StringValue("INVALID");
          }
        default:
          return new BooleanValue(true);
      }
    }

  }
}
