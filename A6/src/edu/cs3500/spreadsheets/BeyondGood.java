package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.view.SpreadsheetView;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {

    // loads graphical view of blank new spreadsheet
    if (args.length == 1 && args[0].equals("-gui")) {
      Worksheet emptyModel = new BasicWorksheet();
      SpreadsheetView emptyView = new SpreadsheetView(emptyModel);
      emptyView.makeVisible();

    } else if (args[0].equals("-in")) {

      Worksheet model = new BasicWorksheet();
      WorksheetReader reader = new WorksheetReader();
      FileReader file = null;
      try {
        file = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      model = reader.read(model.builder(), file);

      model.evaluateAllCells();

      // loads graphical view of requested file evaluated
      if (args.length == 3 && args[2].equals("-gui")) {
        SpreadsheetView view = new SpreadsheetView(model);
        view.makeVisible();

      // opens file and saves it as second file using textual view
      } else if (args.length == 4 && args[2].equals("-save")) {
        PrintWriter pw;
        try {
          pw = new PrintWriter(args[3]);
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("This file doesn't exist");
        }
        WorksheetView wv = new WorksheetTextualView(model, pw);
        wv.render();

      // reads in file, evaluates it, and prints result of given cell
      } else if (args.length == 4 && args[2].equals("-eval")) {
        Coord c = model.createCoord(args[3]);
        String s = model.getContents(c).getVal().toString();
        if (model.getContents(c).getVal() instanceof SNumber) {
          s = String.format("%f", Double.parseDouble(s));
          System.out.println(s);
        } else {
          System.out.println(s);
        }
      }
    }

  }

}
