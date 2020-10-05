import org.junit.Test;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.SpreadsheetController;
import edu.cs3500.spreadsheets.model.AValue;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellWorksheet;
import edu.cs3500.spreadsheets.model.Content;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.ControllableView;
import edu.cs3500.spreadsheets.view.MockView;
import edu.cs3500.spreadsheets.view.SelectPanel;
import edu.cs3500.spreadsheets.view.WorksheetView;

import static org.junit.Assert.assertEquals;


/**
 * Tests methods of Controller class and new View classes.
 */
public class Assignment7Test {

  // MVC
  Worksheet model;
  WorksheetView view;
  Controller controller;

  // Views

  // Components
  Component selectPanel;

  // Model
  private Sexp snum1 = new SNumber(1.0);
  private Sexp snum2 = new SNumber(5.0);
  private Sexp snum3 = new SNumber(10.0);
  private Sexp snum4 = new SNumber(15.0);

  private Sexp sbooltrue = new SBoolean(true);
  private Sexp sboolfalse = new SBoolean(false);

  private Sexp shello = new SString("hello");
  private Sexp sworld = new SString("world");
  private Sexp shi = new SString("hi");

  private Sexp ssymbolsum = new SSymbol("SUM");
  private Sexp ssymbolconcat = new SSymbol("CONCAT");
  private Sexp ssymmbolproduct = new SSymbol("PRODUCT");
  private Sexp ssymbolA2 = new SSymbol("A2");
  private Sexp ssymbolB5 = new SSymbol("B5");
  private Sexp ssymbolA1A3 = new SSymbol("A1:A3");

  private java.util.List<Sexp> list1 = new ArrayList<>(Arrays.asList(ssymbolsum, snum1, snum2));
  private java.util.List<Sexp> list2 = new ArrayList<>(Arrays.asList(snum1, snum2));
  private java.util.List<Sexp> list3 = new ArrayList<>(Arrays.asList(snum2, snum4));
  private List<Sexp> list4 = new ArrayList<>(Arrays.asList(shello, sworld));
  private java.util.List<Sexp> list5 =
          new ArrayList<>(Arrays.asList(ssymmbolproduct, snum2, snum4));

  private Sexp slist1 = new SList(list1);
  private Sexp slist2 = new SList(list2);
  private Sexp slist3 = new SList(list5);

  private AValue dbv1 = new DoubleValue(0.0);
  private AValue dbv2 = new DoubleValue(5.0);
  private AValue dbv3 = new DoubleValue(10.0);
  private AValue dbv4 = new DoubleValue(15.0);

  private AValue sv1 = new StringValue("hello");
  private AValue sv2 = new StringValue("this is a string");
  private AValue sv3 = new StringValue("?");

  private AValue boolvtrue = new BooleanValue(true);
  private AValue boolvfalse = new BooleanValue(false);

  private Content f1 = new Formula(snum1);
  private Content f2 = new Formula(slist1);
  private Content f3 = new Formula(ssymbolA2);
  private Content f4 = new Formula(ssymbolB5);
  private Content f5 = new Formula(ssymbolA1A3);

  private Cell c1 = new Cell(f1);
  private Cell c2 = new Cell(f2);
  private Cell c3 = new Cell(f3);
  private Cell c4 = new Cell(f4);
  private Cell c5 = new Cell(f5);


  /**
   * Creates instances of Coords.
   */
  private Coord coord11 = new Coord(1, 1);
  private Coord coord12 = new Coord(1, 2);
  private Coord coord13 = new Coord(1, 3);
  private Coord coord21 = new Coord(2, 1);
  private Coord coord22 = new Coord(2, 2);
  private Coord coord23 = new Coord(2, 3);
  private Coord coord24 = new Coord(2, 4);
  private Coord coord25 = new Coord(2,5);

  HashMap<Coord, Cell> hash1 = new HashMap<>();

  void initHash1() {
    hash1.put(coord11, c1);
    hash1.put(coord12, c2);
    hash1.put(coord13, c3);
    hash1.put(coord21, c4);
    hash1.put(coord22, c5);
  }

  void initialize() {
    initHash1();
    model = new CellWorksheet(hash1);
    view = new ControllableView(model);
    controller = new SpreadsheetController(model, view);

    selectPanel = new SelectPanel(model);
  }

  //TESTING CONTROLLABLE VIEW
  @Test
  public void addRowTest() {
    initialize();
    assertEquals(8, view.getRectangles().size());
    view.addRow();
    assertEquals(9, view.getRectangles().size());
  }

  @Test
  public void addColTest() {
    initialize();
    assertEquals(25, view.getRectangles().get(0).size());
    view.addCol();
    assertEquals(26, view.getRectangles().get(0).size());
    view.addCol();
    view.addCol();
    view.addCol();
    view.addCol();
    assertEquals(30, view.getRectangles().get(0).size());
  }

  @Test
  public void selectCellTest() {
    initialize();
    view.selectCell(new Point(60, 0));
    assertEquals("B5", view.getInputBoxText());
    view.selectCell(new Point(61, 0));
    assertEquals("B5", view.getInputBoxText());
  }

  @Test
  public void getInputBoxTextTest() {
    initialize();
    assertEquals(25, view.getRectangles().get(0).size());
    view.addCol();
    assertEquals(26, view.getRectangles().get(0).size());
    view.addCol();
    view.addCol();
    view.addCol();
    view.addCol();
    assertEquals(30, view.getRectangles().get(0).size());
  }

  @Test
  public void setInputFieldTest() {
    initialize();
    view.selectCell(new Point(60, 0));
    view.setInputField();
    assertEquals("B5", view.getInputBoxText());
  }

  @Test
  public void getSelectedTest() {
    initialize();
    assertEquals(new Rectangle(0,0,60,20), view.getSelected());
  }

  @Test
  public void getRectanglesTest() {
    initialize();
    assertEquals(8, view.getRectangles().size());
  }

  // TESTING CONTROLLER
  @Test
  public void goControllerTest() {
    initialize();
    StringBuilder log = new StringBuilder();
    MockView mView = new MockView(log);
    controller = new SpreadsheetController(model, mView);
    controller.goController();
    assertEquals("cell selected/ninput recorded/n", log.toString());
  }

  // TESTING NEW MODEL : CELL WORKSHEET
  @Test
  public void getWorksheetTest() {
    initialize();

    assertEquals(hash1, model.getWorksheet());
  }

  @Test
  public void updateCellTest() {
    initialize();

    model.updateCell(coord11, "hello");
    assertEquals(new StringValue("hello"), model.getWorksheet().get(coord11).getContents());

    model.updateCell(coord11, "=(PRODUCT 5 15)");
    assertEquals(new Formula(slist3), model.getWorksheet().get(coord11).getContents());

    model.updateCell(coord12, "A3");
    assertEquals(new StringValue("A3"), model.getWorksheet().get(coord12).getContents());

    model.updateCell(coord12, "=A3");
    assertEquals(new Formula(new SSymbol("A3")), model.getWorksheet().get(coord12).getContents());
  }
}