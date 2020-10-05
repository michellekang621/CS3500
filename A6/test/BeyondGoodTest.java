import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.cs3500.spreadsheets.model.AValue;
import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.ConcatString;
import edu.cs3500.spreadsheets.model.Content;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.model.Product;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.FormulaEvaluator;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class to test the methods within Worksheets and the main method BeyondGood.
 */
public class BeyondGoodTest {

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

  private List<Sexp> list1 = new ArrayList<>(Arrays.asList(ssymbolsum, snum1, snum2));
  private List<Sexp> list2 = new ArrayList<>(Arrays.asList(snum1, snum2));
  private List<Sexp> list3 = new ArrayList<>(Arrays.asList(snum2, snum4));
  private List<Sexp> list4 = new ArrayList<>(Arrays.asList(shello, sworld));


  private Sexp slist1 = new SList(list1);
  private Sexp slist2 = new SList(list2);

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


  HashMap<Coord, Content> hash1 = new HashMap<>();
  HashMap<Coord, Content> hash2 = new HashMap<>();


  Worksheet w0 = new BasicWorksheet(hash1);
  
  /**
   * Initializes a non-empty HashMap for testing.
   */
  public void initHash1() {
    hash1.put(coord11, dbv1);
    hash1.put(coord12, boolvtrue);
    hash1.put(coord13, sv1);
    hash1.put(coord21, dbv3);
    hash1.put(coord22, f1);
    hash1.put(coord23, f3);
    hash1.put(coord24, f2);
  }

  /**
   * Initializes a non-empty HashMap for testing (w/ cyclic references).
   */
  public void initHash2() {
    hash2.put(coord11, dbv1);
    hash2.put(coord12, f5); //indirect cyclic reference to itself
    hash2.put(coord13, sv1);
    hash2.put(coord21, dbv3);
    hash2.put(coord22, f1);
    hash2.put(coord23, f3);
    hash2.put(coord24, f2);
    hash2.put(coord25, f4); //cyclic reference to itself

  }

  private Worksheet w1 = new BasicWorksheet(hash1);
  private Worksheet w2 = new BasicWorksheet(hash2);
  private Worksheet wEqualsw1 = new BasicWorksheet(hash1);
  private Function sum1 = new Sum(w1, coord11, list2);

  private SexpVisitor<AValue> visitbtrue = new FormulaEvaluator(w1, coord12);
  private SexpVisitor<AValue> visitsymbolA2 = new FormulaEvaluator(w1, coord23);





  /**
   * BasicWorksheet tests.
   */

  @Test
  public void testEvaluateAllCells() {
    initHash1();
    w1.evaluateAllCells();
    assertEquals(new DoubleValue(6.0), w1.getContents(coord24));

  }

  @Test
  public void testCreateCoord1() {
    initHash1();
    assertEquals(new Coord(1, 1), w1.createCoord("A1"));
  }

  @Test
  public void testCreateCoord2() {
    initHash1();
    assertEquals(new Coord(3, 4), w1.createCoord("C4"));
  }

  @Test
  public void testCreateCoord3() {
    initHash1();
    assertEquals(new Coord(27, 4), w1.createCoord("AA4"));
  }

  @Test
  public void testCreateCoord4() {
    initHash1();
    assertEquals(new Coord(104, 1), w1.createCoord("CZ1"));
  }

  @Test
          (expected = IllegalArgumentException.class)
  public void testCreateCoord5() {
    initHash1();
    w1.createCoord("CZ0");
  }

  @Test
  public void testCreateCoordList1() {
    initHash1();
    ArrayList<Coord> answerList = w1.createCoordList("A1:A3");
    assertEquals(new ArrayList<Coord>(Arrays.asList(coord11, coord12, coord13)),
            answerList);

  }

  @Test
  public void testCreateCoordList2() {
    initHash1();
    assertEquals(new ArrayList<>(Arrays.asList(coord13)), w1.createCoordList("A3:A4"));
  }

  @Test
          (expected = IllegalArgumentException.class)
  public void testCreateCoordList3() {
    initHash1();
    w1.createCoordList("A1A3");
  }

  @Test
  public void testCreateCoordList4() {
    initHash1();
    assertEquals(new ArrayList<>(Arrays.asList(coord11, coord12, coord13,
            coord21, coord22, coord23, coord24)), w1.createCoordList("A1:B4"));
  }

  @Test
          (expected = IllegalArgumentException.class)
  public void testCreateCoordList5() {
    initHash1();
    w1.createCoordList("B2:A1");
  }

  @Test
  public void testCreateCoordList6() {
    initHash1();
    ArrayList<Coord> answerList = w1.createCoordList("B1:B2");
    assertEquals(new ArrayList<Coord>(Arrays.asList(coord21, coord22)),
            answerList);

  }


  @Test
  public void testIsReference1() {
    initHash1();
    assertEquals(true, w1.isReference("A2"));
  }

  @Test
  public void testIsReference2() {
    initHash1();
    assertEquals(false, w1.isReference("A2A"));
  }

  /**
   * Isn't a valid reference within initHash(), but isReference only checks if the given string,
   * is a Reference at all.
   */
  @Test
  public void testIsReference3() {
    initHash1();
    assertTrue(w1.isReference("A5"));
  }

  @Test
  public void testIsReference4() {
    initHash1();
    assertFalse(w1.isReference("????"));
  }

  @Test
  public void testIsReference5() {
    initHash1();
    assertTrue(w1.isReference("AA2"));
  }

  @Test
  public void testIsRange1() {
    initHash1();
    assertTrue(w1.isRange("A1:A2"));
  }

  @Test
  public void testIsRange2() {
    initHash1();
    assertEquals(false, w1.isRange("A1A2"));
  }

  @Test
  public void testIsRange3() {
    initHash1();
    assertEquals(true, w1.isRange("B1:B2"));
  }

  @Test
  public void testIsRange4() {
    initHash1();
    assertFalse(w1.isRange("B1:A2"));
  }

  @Test
  public void testIsRange5() {
    initHash1();
    assertFalse(w1.isRange("A3:A1"));
  }

  @Test
  public void testIsRange6() {
    initHash1();
    assertTrue(w1.isRange("A1:A100"));
  }
  
  @Test
  public void testIsCyclic1() {
    initHash2();
    assertTrue(w2.isCyclic(coord25, f4));
  }

  @Test
  public void testIsCyclic2() {
    initHash1();
    assertFalse(w1.isCyclic(coord12, dbv1));
  }

  @Test
  public void testIsCyclic3() {
    initHash2();
    assertTrue(w2.isCyclic(coord25, f4));
  }
  
  @Test
  public void testGetContents1() {
    initHash1();
    assertEquals(new DoubleValue(0.0), w1.getContents(coord11));
  }

  @Test
  public void testGetContents2() {
    initHash1();
    assertEquals(new BooleanValue(true), w1.getContents(coord12));
  }

  @Test
  public void testGetContents3() {
    initHash1();
    assertEquals(new StringValue("hello"), w1.getContents(coord13));
  }

  @Test
  public void testGetContents4() {
    initHash1();
    assertEquals(new Formula(new SSymbol("A2")), w1.getContents(coord23));
  }

  @Test
  public void testSum1() {
    initHash1();
    assertEquals(new DoubleValue(6.0), new Sum(w1, coord24, list2).evaluate());
  }

  @Test
  public void testProduct1() {
    initHash1();
    assertEquals(new DoubleValue(75.0), new Product(w1, coord11, list3).evaluate());
  }

  @Test
  public void testLessThan() {
    initHash1();
    assertEquals(new BooleanValue(true), new LessThan(w1, coord11, snum2, snum4).evaluate());
  }

  @Test
  public void testConcat() {
    initHash1();
    assertEquals(new StringValue("hello world"),
            new ConcatString(w1, coord11, list4).evaluate());
  }
  
  
  

  /**
   * AValue class tests.
   */
  @Test
  public void testEvaluate1() {
    initHash1();
    assertEquals(dbv1, dbv1.evaluate(w1, coord11));
  }

  /**
   * FormulaEvaluator tests.
   */
  @Test
  public void testVisitBoolean() {
    assertEquals(new BooleanValue(true), visitbtrue.visitBoolean(true));
  }

  @Test
  public void testVisitSymbol() {
    initHash1();
    assertEquals(new BooleanValue(true), visitsymbolA2.visitSymbol("A2"));
  }

  @Test
  public void testEquals() {
    initHash1();
    initHash2();
    assertFalse(w1.equals(w2));
  }

  @Test
  public void testEquals2() {
    initHash1();
    initHash2();
    assertTrue(w1.equals(wEqualsw1));
  }

  @Test
  public void toStringDoubleValue() {
    assertEquals("5.0", dbv2.toString());
    assertEquals("15.0", dbv4.toString());

  }

  @Test
  public void toStringStringValue() {
    assertEquals("hello", sv1.toString());
    assertEquals("?", sv3.toString());
  }

  @Test
  public void toStringBooleanValue() {
    assertEquals("true", boolvtrue.toString());
    assertEquals("false", boolvfalse.toString());
  }

  @Test
  public void toStringFormula() {
    assertEquals("1.0", f1.toString());
    assertEquals("(SUM 1.0 5.0)", f2.toString());
    assertEquals("A2", f3.toString());
    assertEquals("B5", f4.toString());
    assertEquals("A1:A3", f5.toString());
  }

  @Test
  public void testNonEmptyCoords() {
    ArrayList<Coord> cList = new ArrayList<>();
    assertEquals(cList, w0.nonEmptyCoords());

    initHash1();
    cList = w0.nonEmptyCoords();
    HashMap<Coord, Content> hashTemp = new HashMap<>();
    for (int i = 0; i < cList.size(); i++) {
      Coord c = cList.get(i);
      hashTemp.put(c, w0.getContents(c));
    }
    Worksheet tempW = new BasicWorksheet(hashTemp);
    assertEquals(tempW, w0);
  }
}
