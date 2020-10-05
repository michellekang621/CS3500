package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that represents a type of Function that can within a Worksheet.
 */
public class LessThan implements Function {
  Worksheet w;
  Coord c;
  Sexp s1;
  Sexp s2;

  /**
   * Creates an instance of the class LessThan.
   */
  public LessThan(Worksheet w, Coord c, Sexp s1, Sexp s2) {
    this.w = w;
    this.c = c;
    this.s1 = s1;
    this.s2 = s2;
  }

  @Override
  public AValue evaluate() {
    AValue newS = s1.accept(new FormulaEvaluator(this.w, this.c));
    AValue newS2 = s2.accept(new FormulaEvaluator(this.w, this.c));
    return newS.lessThan(newS2);
  }
}
