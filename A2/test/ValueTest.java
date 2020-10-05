import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.Value;

/**
 * Tests methods in the Value enum work properly.
 */
public class ValueTest {

  Value v10 = Value.Ten;
  Value v13 = Value.King;


  @Test
  public void getValue() {
    assertEquals(10, this.v10.getValue());
    assertEquals(13, this.v13.getValue());
  }
}