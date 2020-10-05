package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that represents a type of Function that can within a Worksheet.
 */
public class Product implements Function {
  Worksheet w;
  Coord c;
  List<Sexp> s;

  /**
   * Creates an instance of the class Product.
   */
  public Product(Worksheet w, Coord c, List<Sexp> s) {
    this.w = w;
    this.c = c;
    this.s = s;
  }

  @Override
  public AValue evaluate() {
    AValue v = new DoubleValue(1.0);
    for (Sexp e : s) {
      v = v.multiply(e.accept(new FormulaEvaluator(this.w, this.c)));
    }

    return v;
  }
}
