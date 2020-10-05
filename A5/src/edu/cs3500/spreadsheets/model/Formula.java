package edu.cs3500.spreadsheets.model;

import java.util.Objects;

import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * A class representing a possible Content that can be contained within a Coord within a Worksheet.
 */
public class Formula implements Content {
  Sexp contents;

  public Formula(Sexp contents) {
    this.contents = contents;
  }

  @Override
  public AValue evaluate(Worksheet w, Coord c) {
    return this.contents.accept(new FormulaEvaluator(w, c));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Formula formula = (Formula) o;

    return contents.equals(formula.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.contents);
  }

  @Override
  public String toString() {
    return contents.toString();
  }

  @Override
  public Sexp getVal() {
    return this.contents;
  }

}