package edu.cs3500.spreadsheets.model;

/**
 * Represents a Cell in the Worksheet.
 */
public class Cell {
  Content c;

  /**
   * Constructs a standard non-empty Cell with the given Content.
   * @param c the Content inside this Cell.
   */
  public Cell(Content c) {
    this.c = c;
  }

  public Content getContents() {
    return this.c;
  }
}
