import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellWorksheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;

import static junit.framework.TestCase.assertTrue;

/**
 * Tests that render method in WorksheetTextualView works correctly.
 */
public class WorksheetTextualViewTest {

  Worksheet w;
  WorksheetReader wr;
  PrintWriter pw;
  WorksheetTextualView wtv;
  FileReader f;


  void initialize() {
    w = new CellWorksheet();
    wr = new WorksheetReader();
    f = null;

    try {
      f = new FileReader("tester.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    w = wr.read(w.builder(), f);
    w.evaluateAllCells();

    try {
      pw = new PrintWriter("output.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("This file doesn't exist");
    }

    this.wtv = new WorksheetTextualView(w, pw);
  }

  @Test
  public void testRender() {
    this.initialize();
    wtv.render();
    FileReader f2 = null;
    try {
      f2 = new FileReader("output.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Worksheet w2 = new CellWorksheet();
    WorksheetReader wr2 = new WorksheetReader();
    w2 = wr2.read(w2.builder(), f2);
    HashMap<Coord, Cell> ww2 = w2.evaluateAllCells();
    HashMap<Coord, Cell> ww = w.getWorksheet();
    assertTrue(ww.size() == ww2.size());

  }
}
