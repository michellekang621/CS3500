package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.provider.view.Cell;
import edu.cs3500.spreadsheets.provider.view.CellAttributes;
import edu.cs3500.spreadsheets.provider.view.Coord;
import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * An adapter of Provider's Cell interface to make Provider's Cell work with our model.
 */
public class CellAdapter implements Cell {

  Worksheet ourWorksheet;
  CellAttributes attributes;
  String newContent;
  WorksheetAdapter theirWorksheet;
  Coord c;

  /**
   * Creates an instance of Provider's cell using this adapter using our Worksheet,
   * the cell's location, and the content to fill the cell with.
   * @param ourWorksheet our model's worksheet
   * @param newContent the content to fill the cell with
   * @param c the location of this Cell
   */
  public CellAdapter(Worksheet ourWorksheet, String newContent, Coord c) {
    this.ourWorksheet = ourWorksheet;
    this.newContent = newContent;
    this.attributes = new CellAttributes();
    this.theirWorksheet = new WorksheetAdapter(ourWorksheet);
    this.c = c;
  }

  @Override
  public ArrayList<Coord> references() {
    edu.cs3500.spreadsheets.model.Coord ourC =
            new edu.cs3500.spreadsheets.model.Coord(c.col, c.row);
    ArrayList<Coord> theirList = new ArrayList<>();
    ArrayList<edu.cs3500.spreadsheets.model.Coord> ourList;
    ourList = ourWorksheet.createCoordList(ourWorksheet.getContents(ourC).toString());
    for (edu.cs3500.spreadsheets.model.Coord c : ourList) {
      Coord theirC = new Coord(ourC.col, ourC.row);
      theirList.add(theirC);
    }

    return theirList;
  }

  @Override
  public Formula getFormula() {
    return null;
  }

  @Override
  public String getCellName() {
    return this.c.toString();
  }

  @Override
  public String evaluate(edu.cs3500.spreadsheets.provider.view.Worksheet worksheet, boolean clean) {
    Parser p = new Parser();
    String s = worksheet.getCellAt(this.c).getRawContents();
    if (s != null && !s.equals("")) {
      Sexp sexp = p.parse(s);
      edu.cs3500.spreadsheets.model.Coord ourC =
              new edu.cs3500.spreadsheets.model.Coord(c.col, c.row);
      FormulaEvaluator fe = new FormulaEvaluator(this.ourWorksheet, ourC);
      return sexp.accept(fe).toString();
    }

    return "";
  }

  @Override
  public boolean cyclicReference(Coord location) {
    edu.cs3500.spreadsheets.model.Coord ourC =
            new edu.cs3500.spreadsheets.model.Coord(c.col, c.row);
    return ourWorksheet.isCyclic(ourC, ourWorksheet.getContents(ourC));
  }

  @Override
  public boolean directCyclicReference() {
    edu.cs3500.spreadsheets.model.Coord ourC =
            new edu.cs3500.spreadsheets.model.Coord(c.col, c.row);
    return ourWorksheet.getContents(ourC).evaluate(ourWorksheet, ourC)
            .toString().equals(ourC.toString());
  }

  @Override
  public String getRawContents() {
    return this.newContent;
  }

}
