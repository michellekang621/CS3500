package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that represents a type of Function that can within a Worksheet.
 */
public class Sum implements Function {
  Worksheet w;
  Coord c;
  List<Sexp> s;

  /**
   * Creates an instance of the class Sum.
   */
  public Sum(Worksheet w, Coord c, List<Sexp> s) {
    this.w = w;
    this.c = c;
    this.s = s;
  }


  @Override
  public AValue evaluate() {
    AValue v = new DoubleValue(0.0);
    for (Sexp exp : s) {
      if (w.isReference(exp.toString())) {
        Coord coord = w.createCoord(exp.toString());
        Content c = w.getContents(coord);
        v = v.add(c.evaluate(this.w, coord));
      } else if (w.isRange(exp.toString())) {
        ArrayList<Coord> list = w.createCoordList(exp.toString());
        for (int i = 0; i < list.size(); i++) {
          Coord coord = list.get(i);
          Content c = w.getContents(coord);
          v = v.add(c.evaluate(this.w, coord));
        }
      } else {
        v = v.add(exp.accept(new FormulaEvaluator(this.w, this.c)));
      }
    }
    return v;
  }
}
