import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardState;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;

/**
 * Tests all the methods in the Card class.
 */
public class CardTest {

  Card c1 = new Card(Symbol.Club, Value.Ten);
  Card c2 = new Card(Symbol.Club, Value.Ten);
  Card c3 = new Card(Symbol.Heart, Value.Jack);

  @Test
  public void testEquals1() {
    assertTrue(this.c1.equals(this.c2));
  }

  @Test
  public void testHashCode1() {
    assertEquals(50, this.c1.hashCode());
  }

  @Test
  public void testToString1() {
    assertEquals("J♥", this.c3.toString());
    assertEquals("10♣", this.c1.toString());
  }

  @Test
  public void testReturnState() {
    assertEquals(CardState.Other, this.c1.returnState());
  }

  @Test
  public void testGetValue() {
    assertEquals(Value.Jack, this.c3.getValue());
    assertEquals(Value.Ten, this.c2.getValue());
  }
}