package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a Worksheet made up of Cells.
 */
public class CellWorksheet implements Worksheet {
  private final HashMap<Coord, Cell> worksheet;


  /**
   * Constructs an instance of a CellWorksheet using the given worksheet.
   *
   * @param worksheet the worksheet to construct the CellWorksheet from
   */
  public CellWorksheet(HashMap<Coord, Cell> worksheet) {
    this.worksheet = worksheet;
  }

  /**
   * A Builder class that builds the type of Worksheet BasicWorksheet. Each type of Worksheet that
   * implements Worksheet will require a Builder class like this.
   */
  public static final class CellWorksheetBuilder
          implements WorksheetReader.WorksheetBuilder<CellWorksheet> {
    private HashMap<Coord, Cell> worksheet = new HashMap<>();
    private Appendable a = new StringBuilder();

    public CellWorksheet.CellWorksheetBuilder worksheet(HashMap<Coord, Cell> worksheet) {
      this.worksheet = worksheet;
      return this;
    }

    public CellWorksheet build() {
      return new CellWorksheet(this);
    }

    @Override
    public WorksheetReader.WorksheetBuilder<CellWorksheet> createCell(
            int col, int row, String contents) {
      Coord c = new Coord(col, row);
      Parser p = new Parser();
      Content ct;
      String[] parsed = contents.split("");
      if (contents.substring(0, 1).equals("=")) {
        ct = new Formula(p.parse(contents.substring(1)));
      } else {
        if (contents.equals("true")) {
          ct = new BooleanValue(true);
        } else if (contents.equals("false")) {
          ct = new BooleanValue(false);
        } else if (parsed[0].equals("\"") && parsed[parsed.length - 1].equals("\"")) {
          ct = new StringValue(contents);
        } else if (isRangeBuilder(contents) || isReferenceBuilder(contents)) {
          ct = new Formula(new SSymbol(contents));
        } else {
          try {
            ct = new DoubleValue(Double.parseDouble(contents));
          } catch (Exception e) {
            ct = new StringValue("INVALID");
          }
        }
      }
      worksheet.put(c, new Cell(ct));
      return this;
    }

    @Override
    public CellWorksheet createWorksheet() {
      return new CellWorksheet(worksheet);
    }

    private Coord createCoordBuilder(String c) {
      String[] cParsed = c.split("");
      String s = "";
      String num = "";

      int numIdx = 0;
      for (int i = 0; i < c.length(); i++) {
        if (isLetterBuilder(cParsed[i])) {
          s += cParsed[i];
        } else {
          numIdx = i;
          break;
        }
      }

      for (int i = numIdx; i < c.length(); i++) {
        num += cParsed[i];
      }

      if (Coord.colNameToIndex(s) < 1 || Integer.parseInt(num) < 1) {
        throw new IllegalArgumentException("Not a valid Coordinate");
      }

      return new Coord(Coord.colNameToIndex(s), Integer.parseInt(num));
    }


    private boolean isReferenceBuilder(String s) throws IllegalArgumentException {
      String[] sParse = s.split("");
      String firstStr = sParse[0];

      if (s.length() < 2 || !isLetterBuilder(firstStr)) {
        return false;
      } else {
        int counter = 0;
        for (int i = 1; i < sParse.length; i++) {
          String prev = sParse[i - 1];
          String curr = sParse[i];

          if (isLetterBuilder(prev) && !isLetterBuilder(curr)) {
            counter++;
          } else if (isLetterBuilder(curr) && !isLetterBuilder(prev)) {
            counter++;
          }
        }

        if (counter != 1) {
          return false;
        }
      }

      return true;

    }

    private boolean isRangeBuilder(String s) {
      if (!s.contains(":")) {
        return false;
      }
      String[] cParsed = s.split(":");
      Coord start = this.createCoordBuilder(cParsed[0]);
      Coord end = this.createCoordBuilder(cParsed[1]);

      if (start.row > end.row || start.col > end.col) {
        throw new IllegalArgumentException("Not a valid range.");
      }
      return cParsed.length == 2
              && isReferenceBuilder(cParsed[0]) && isReferenceBuilder(cParsed[1]);
    }

    private boolean isLetterBuilder(String s) {
      try {
        Integer.parseInt(s);
      } catch (Exception e) {
        return true;
      }
      return false;
    }


  }

  /**
   * Constructs a CellWorksheet using the builder.
   *
   * @param builder the builder that will construct the CellWorksheet.
   */
  public CellWorksheet(CellWorksheet.CellWorksheetBuilder builder) {
    this.worksheet = builder.worksheet;
  }

  /**
   * Empty constructor for testing.
   */
  public CellWorksheet() {
    this.worksheet = new HashMap<>();
  }

  @Override
  public CellWorksheet.CellWorksheetBuilder builder() {
    return new CellWorksheet.CellWorksheetBuilder();
  }

  @Override
  // evaluates all contents of the cells in this worksheet and mutates them
  public HashMap<Coord, Cell> evaluateAllCells() {

    HashMap<Coord, Cell> evaluatedWS = new HashMap<>();
    for (Coord c : worksheet.keySet()) {
      Cell cell = worksheet.get(c);
      evaluatedWS.put(c, new Cell(cell.getContents().evaluate(this, c)));
    }

    return evaluatedWS;
  }

  @Override
  // changes a string coordinate into a Coord
  public Coord createCoord(String c) {
    String[] cParsed = c.split("");
    String s = "";
    String num = "";

    int numIdx = 0;
    for (int i = 0; i < c.length(); i++) {
      if (isLetter(cParsed[i])) {
        s += cParsed[i];
      } else {
        numIdx = i;
        break;
      }
    }

    for (int i = numIdx; i < c.length(); i++) {
      num += cParsed[i];
    }

    if (Coord.colNameToIndex(s) < 1 || Integer.parseInt(num) < 1) {
      throw new IllegalArgumentException("Not a valid Coordinate");
    }

    return new Coord(Coord.colNameToIndex(s), Integer.parseInt(num));
  }


  @Override
  public ArrayList<Coord> createCoordList(String s) throws IllegalArgumentException {
    if (!isRange(s)) {
      throw new IllegalArgumentException("Not a range!");
    } else {

      String[] cParsed = s.split(":");
      Coord start = this.createCoord(cParsed[0]);
      Coord end = this.createCoord(cParsed[1]);


      ArrayList<Coord> list = new ArrayList<>();
      for (int c = start.col; c <= end.col; c++) {
        for (int r = start.row; r <= end.row; r++) {
          if (worksheet.containsKey(new Coord(c, r))) {
            list.add(new Coord(c, r));
          }
        }
      }
      return list;
    }

  }

  @Override
  public boolean isReference(String s) {
    String[] sParse = s.split("");
    String firstStr = sParse[0];

    if (s.length() < 2 || !isLetter(firstStr)) {
      return false;
    } else {
      int counter = 0;
      for (int i = 1; i < sParse.length; i++) {
        String prev = sParse[i - 1];
        String curr = sParse[i];

        if (isLetter(prev) && !isLetter(curr)) {
          counter++;
        } else if (isLetter(curr) && !isLetter(prev)) {
          counter++;
        }
      }

      if (counter != 1) {
        return false;
      }
    }

    return true;

  }

  @Override
  public boolean isRange(String s) throws IllegalArgumentException {
    if (!s.contains(":")) {
      return false;
    }
    String[] cParsed = s.split(":");
    try {
      Coord start = this.createCoord(cParsed[0]);
      Coord end = this.createCoord(cParsed[1]);
      if (start.row > end.row || start.col > end.col) {
        throw new IllegalArgumentException("Not a valid range.");
      }
      return cParsed.length == 2 && isReference(cParsed[0]) && isReference(cParsed[1]);
    } catch (Exception e) {
      return false;
    }


  }

  @Override
  public Content getContents(Coord coordinate) throws IllegalArgumentException {
    if (!worksheet.containsKey(coordinate)) {
      return new StringValue("");
    } else if (isCyclic(coordinate, worksheet.get(coordinate).getContents())) {
      throw new IllegalArgumentException("This cell is cyclic.");
    } else {
      return worksheet.get(coordinate).getContents();
    }
  }

  @Override
  public boolean isCyclic(Coord initial, Content content) {
    String s = content.getVal().toString();
    if (isReference(s)) {
      if (s.equals(initial.toString())) {
        return true;
      } else {
        if (worksheet.containsKey(createCoord(s))) {
          return isCyclic(initial, worksheet.get(createCoord(s)).getContents());
        } else {
          return false;
        }
      }
    } else if (isRange(s)) {
      ArrayList<Coord> list = createCoordList(s);
      boolean notCyclic = true;
      for (Coord coord : list) {
        if (isCyclic(initial, worksheet.get(coord).getContents())) {
          notCyclic = false;
        }
      }
      return notCyclic;
    } else {
      return false;
    }

  }

  @Override
  public ArrayList<Coord> nonEmptyCoords() {
    ArrayList<Coord> coords = new ArrayList<>();
    for (Coord c : worksheet.keySet()) {
      coords.add(c);
    }

    return coords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CellWorksheet ws = (CellWorksheet) o;

    boolean b = true;

    for (Coord c : ws.worksheet.keySet()) {
      if (this.worksheet.containsKey(c)) {
        b = b && (this.worksheet.get(c).equals(ws.worksheet.get(c)));
      } else {
        b = b && this.worksheet.containsKey(c);
      }
    }

    b = b && (ws.worksheet.size() == this.worksheet.size());

    return b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(worksheet);
  }

  /**
   * Determines whether the given String represents a letter of the alphabet.
   *
   * @param s is the given String
   * @return true if the String represents a letter of the alphabet
   */
  private boolean isLetter(String s) {
    try {
      Integer.parseInt(s);
    } catch (Exception e) {
      return true;
    }
    return false;
  }

  @Override
  public void updateCell(Coord c, String s) {
    if (!this.worksheet.containsKey(c)) {
      this.worksheet.put(c, new Cell(new StringValue("")));
    }
    String[] parsedStr = s.split("");
    Parser p = new Parser();
    Sexp sexp;

    if (parsedStr[0].equals("=")) {
      s = s.substring(1);
      try {
        sexp = p.parse(s);
        worksheet.put(c, new Cell(new Formula(sexp)));
      } catch (Exception e) {
        worksheet.put(c, new Cell(new StringValue("INVALID")));
      }
    } else {
      worksheet.put(c, new Cell(new StringValue(s)));
    }

  }


  @Override
  public HashMap<Coord, Cell> getWorksheet() {
    HashMap<Coord, Cell> copy = new HashMap<>();

    copy.putAll(this.worksheet);
    return copy;
  }

}
