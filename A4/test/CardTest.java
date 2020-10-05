import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardState;
import cs3500.pyramidsolitaire.model.hw02.Symbol;
import cs3500.pyramidsolitaire.model.hw02.Value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests all the methods in the Card class.
 */
public class CardTest {

  private Card c1 = new Card(Symbol.Club, Value.Ten);
  private Card c2 = new Card(Symbol.Club, Value.Ten);
  private Card c3 = new Card(Symbol.Heart, Value.Jack);

  @Test
  public void testEquals() {
    assertTrue(this.c1.equals(this.c2));
  }

  @Test
  public void testHashCode() {
    assertEquals(50, this.c1.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("J♥", this.c3.toString());
    assertEquals("10♣", this.c1.toString());
  }

  @Test
  public void testReturnState() {
    assertEquals(CardState.Other, this.c1.returnState());
  }

  @Test
  public void testGetValue() {
    assertEquals(11, this.c3.getValue());
    assertEquals(10, this.c2.getValue());
  }
}