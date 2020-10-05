import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.Symbol;

import static org.junit.Assert.assertEquals;

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