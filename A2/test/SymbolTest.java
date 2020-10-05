import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.Symbol;

/**
 * Tests methods in the Symbol enum works properly.
 */
public class SymbolTest {

  Symbol sC = Symbol.Club;
  Symbol sH = Symbol.Heart;

  @Test
  public void getValue() {
    assertEquals("♣", this.sC.getValue());
    assertEquals("♥", this.sH.getValue());
  }
}