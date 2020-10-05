package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.SNumber;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */


    // TODO: VALIDITY CHECK
    Worksheet w = new BasicWorksheet();
    WorksheetReader reader = new WorksheetReader();
    FileReader file = null;
    try {
      file = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    w = reader.read(w.builder(), file);

    w.evaluateAllCells();
    Coord c = w.createCoord(args[3]);
    w.getContents(c).evaluate(w, c);
    String s = w.getContents(c).getVal().toString();
    if (w.getContents(c).getVal() instanceof SNumber) {
      s = String.format("%f", Double.parseDouble(s));
      System.out.print(s);
    } else {
      System.out.print(s);
    }
  }


}
