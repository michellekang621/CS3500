package edu.cs3500.spreadsheets.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.provider.view.CellAttribute;
import edu.cs3500.spreadsheets.provider.view.CellAttributes;
import edu.cs3500.spreadsheets.provider.view.Cell;
import edu.cs3500.spreadsheets.provider.view.Coord;

/**
 * An adapter of Provider's Worksheet interface to make Provider's view work with our model.
 */
public class WorksheetAdapter implements edu.cs3500.spreadsheets.provider.view.Worksheet {

  private edu.cs3500.spreadsheets.model.Worksheet ourWorksheet;

  /**
   * Creates a new worksheet.
   */
  public WorksheetAdapter() {
    this.ourWorksheet = new CellWorksheet();
  }

  /**
   * Constructs an existing worksheet.
   * @param ourWorksheet the existing worksheet passed in in the form of our Model.
   */
  public WorksheetAdapter(Worksheet ourWorksheet) {
    this.ourWorksheet = ourWorksheet;
  }


  @Override
  public Cell getCellAt(Coord coord) {
    edu.cs3500.spreadsheets.model.Coord c =
            new edu.cs3500.spreadsheets.model.Coord(coord.col, coord.row);
    return new CellAdapter(ourWorksheet, ourWorksheet.getContents(c).toString(), coord);
  }

  @Override
  public void setCell(Coord coord, Cell cell) {
    edu.cs3500.spreadsheets.model.Coord c =
            new edu.cs3500.spreadsheets.model.Coord(coord.col, coord.row);
    ourWorksheet.updateCell(c, cell.getRawContents());
  }

  @Override
  public HashMap<Coord, Cell> getWorksheet() {
    HashMap<edu.cs3500.spreadsheets.model.Coord,
            edu.cs3500.spreadsheets.model.Cell> map = ourWorksheet.getWorksheet();
    HashMap<Coord, Cell> newMap = new HashMap<>();

    for (Map.Entry<edu.cs3500.spreadsheets.model.Coord,
            edu.cs3500.spreadsheets.model.Cell> entry : map.entrySet()) {

      edu.cs3500.spreadsheets.model.Coord c = entry.getKey();
      edu.cs3500.spreadsheets.model.Cell cell = entry.getValue();
      edu.cs3500.spreadsheets.provider.view.Coord theirC = new Coord(c.col, c.row);


      newMap.put(theirC, new CellAdapter(ourWorksheet,
              this.getCellAt(theirC).getRawContents(), theirC));
    }

    return newMap;
  }

  @Override
  public Formula getCalculatedReference(Coord coord) {
    return null;
  }


  @Override
  public boolean hasCalculatedReference(Coord coord) {
    return false;
  }


  @Override
  public void clearCalculatedReferences() {
    // not implemented.
  }

  @Override
  public int getHeight() {
    HashMap<Coord, Cell> map = this.getWorksheet();
    int maxY = 0;
    for (Coord c : map.keySet()) {
      if (c.row > maxY) {
        maxY = c.row;
      }
    }

    return maxY;
  }

  @Override
  public int getWidth() {
    HashMap<Coord, Cell> map = this.getWorksheet();
    int maxX = 0;
    for (Coord c : map.keySet()) {
      if (c.col > maxX) {
        maxX = c.col;
      }
    }

    return maxX;
  }


  @Override
  public ArrayList<Coord> referTo(Coord coord) {
    return null;
  }


  @Override
  public boolean containsCyclicReference(Coord coord) {
    return false;
  }


  @Override
  public void addCyclicReference(Coord coord) {
    // not implemented.
  }


  @Override
  public void removeCyclicReference(Coord coord) {
    // not implemented.
  }


  @Override
  public void toggleAttribute(CellAttribute attribute, Coord coord) {
    // not implemented.
  }

  @Override
  public void setColor(CellAttribute attribute, Color color, Coord coord) {
    // not implemented.
  }

  @Override
  public CellAttributes getAttributeSet(Coord coord) {
    return new CellAttributes();
  }

  @Override
  public HashMap<Coord, CellAttributes> getAttributes() {
    HashMap<Coord, Cell> map = this.getWorksheet();
    HashMap<Coord, CellAttributes> newMap = new HashMap<>();

    for (Coord c : map.keySet()) {
      newMap.put(c, new CellAttributes());
    }

    return newMap;
  }

  @Override
  public void setAttributes(Coord coord, CellAttributes attributeSet) {
    // not implemented.
  }

  @Override
  public void addCalculatedReference(Coord coord, Formula formula) {
    // not implemented.
  }

  public edu.cs3500.spreadsheets.provider.view.Worksheet getModel() {
    return this;
  }
}
