package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that represents a type of Function that can within a Worksheet.
 */
public class ConcatString implements Function {
  Worksheet w;
  Coord c;
  List<Sexp> s;

  /**
   * Creates an instance of the class ConcatString.
   */
  public ConcatString(Worksheet w, Coord c, List<Sexp> s) {
    this.w = w;
    this.c = c;
    this.s = s;
  }

  @Override
  public AValue evaluate() {
    AValue strAns = s.get(0).accept(new FormulaEvaluator(this.w, this.c));
    for (int i = 1; i < s.size(); i++) {
      strAns = strAns.concatString(s.get(i).accept(new FormulaEvaluator(this.w, this.c)));
    }

    return strAns;

  }
}