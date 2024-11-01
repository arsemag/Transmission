import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests out different situations for the Regular Manual Transmission.
 */
public class RegularManualTransmissionTest {

  private String everythingIsOkay;

  private String increaseGear;
  private String decreaseGear;

  private String cannotIncreaseSpeedIncreaseGear;
  private String cannotDecreaseSpeedDecreaseGear;

  private String cannotIncreaseGearIncreaseSpeed;
  private String cannotDecreaseGearDecreaseSpeed;

  private String cannotIncreaseSpeedMax;
  private String cannotDecreaseSpeedMin;


  private String cannotIncreaseGearMax;
  private String cannotDecreaseGearMin;
  private ManualTransmission goodCar;


  private int changeSpeed(ManualTransmission car, int speedChange) {
    int speed = car.getSpeed();

    if (speedChange > 0) {
      for (int i = 0; i < speedChange; i++) {
        car.increaseSpeed();
        speed = car.getSpeed();
      }
    } else if (speedChange < 0) {
      for (int i = 0; i > speedChange; i--) {
        car.decreaseSpeed();
        speed = car.getSpeed();
      }
    }
    return speed;
  }


  @Before
  public void setup() {
    this.everythingIsOkay = "OK: everything is OK.";
    this.increaseGear = "OK: you may increase the gear.";
    this.decreaseGear = "OK: you may decrease the gear.";

    this.cannotIncreaseSpeedIncreaseGear = "Cannot increase speed, increase gear first.";

    this.cannotDecreaseSpeedDecreaseGear = "Cannot decrease speed, decrease gear first.";
    this.cannotIncreaseGearIncreaseSpeed = "Cannot increase gear, increase speed first.";
    this.cannotDecreaseGearDecreaseSpeed = "Cannot decrease gear, decrease speed first.";

    this.cannotIncreaseSpeedMax = "Cannot increase speed. Reached maximum speed.";
    this.cannotDecreaseSpeedMin = "Cannot decrease speed. Reached minimum speed.";

    this.cannotIncreaseGearMax = "Cannot increase gear. Reached maximum gear.";
    this.cannotDecreaseGearMin = "Cannot decrease gear. Reached minimum gear.";
    this.goodCar = new RegularManualTransmission(0, 10, 5, 20, 15,
            30, 25, 40, 35, 50);


  }

  @Test
  public void testGoodConstructor() {
    try {

      ManualTransmission goodCar = new RegularManualTransmission(0, 10, 5, 20, 15,
              30, 25, 40, 35, 50);
    } catch (IllegalArgumentException e) {

      fail("Constructor should not throw an IllegalArgumentException with valid arguments");
    }

  }



  @Test(expected = IllegalArgumentException.class)
  public void testLowerGear() {

    ManualTransmission wrong = new RegularManualTransmission(5, 10, 15, 20, 25,
            30, 35, 40, 45, 50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNegativeLowerSpeedDoesNotStartWithZero() {


    ManualTransmission wrong = new RegularManualTransmission(-5, 10, 15, 20, 25,
            30, 35, 40, 45, 50);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testNonAdjecentSpeed() {

    ManualTransmission wrong = new RegularManualTransmission(0, 5, 15, 20, 25,
            30, 35, 40, 45, 50);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneSpeedHigherThanMaximumSpeed() {


    ManualTransmission wrong = new RegularManualTransmission(0, 10, 80, 20, 25,
            30, 35, 40, 45, 50);
  }



  @Test
  public void getSpeedStartOfCar() {

    assertEquals( 0, goodCar.getSpeed());
    assertEquals( 1, goodCar.getGear());
    assertEquals(everythingIsOkay, goodCar.getStatus());

  }

  @Test
  public void getSpeedWhenMoving() {
    goodCar.increaseSpeed();

    assertEquals(1, goodCar.getSpeed());
  }





  @Test
  public void getGear() {

    assertEquals(0, goodCar.getSpeed());

    goodCar.increaseSpeed();

    assertEquals(1, goodCar.getGear());

  }

  @Test
  public void getGearAtOverlappingNumber() {

    changeSpeed(goodCar, 10);

    assertEquals(10, goodCar.getSpeed());
    assertEquals(increaseGear, goodCar.getStatus());

    assertEquals(1, goodCar.getGear());
  }



  @Test
  public void increaseSpeedWhenReachedMaximumLimitInGear() {

    assertEquals(1, goodCar.getGear());
    changeSpeed(goodCar, 16);

    assertEquals(10, goodCar.getSpeed());
    assertEquals(1, goodCar.getGear());

    goodCar.increaseSpeed();

    assertEquals(10, goodCar.getSpeed());

    assertEquals(cannotIncreaseSpeedIncreaseGear, goodCar.getStatus());
  }

  @Test
  public void increaseSpeedRegularNothing() {

    goodCar.increaseSpeed();
    goodCar.increaseSpeed();

    assertEquals(2, goodCar.getSpeed());

    assertEquals(everythingIsOkay, goodCar.getStatus());
  }


  @Test
  public void decreaseSpeedNothingHappend() {
    changeSpeed(goodCar, 5);
    assertEquals(5, goodCar.getSpeed());


    changeSpeed(goodCar, -1);
    assertEquals(4, goodCar.getSpeed());
    assertEquals(everythingIsOkay, goodCar.getStatus());
  }


  @Test
  public void decreaseSpeedOverLappingGear() {
    changeSpeed(goodCar, 11);
    goodCar.increaseGear();

    assertEquals(2, goodCar.getGear());
    goodCar.decreaseSpeed();

    assertEquals(2, goodCar.getGear());
    assertEquals(decreaseGear, goodCar.getStatus());
  }


  // when increasing the gear and nothing happens
  @Test
  public void increaseGearAtRegularNothing() {
    assertEquals(1, goodCar.getGear());
    changeSpeed(goodCar, 5);

    goodCar.increaseGear();
    assertEquals(2, goodCar.getGear());
    assertEquals(everythingIsOkay, goodCar.getStatus());
  }


  //
  @Test
  public void decreaseGear() {
    changeSpeed(goodCar, 5);
    goodCar.increaseGear();
    assertEquals(2, goodCar.getGear());

    goodCar.decreaseGear();
    assertEquals(1, goodCar.getGear());

  }


  // eveything is okay increasing the gear
  @Test
  public void testMessageEverythingIsOkayForGearIncrease() {

    // starts at gear 1
    assertEquals(1, goodCar.getGear());

    // in gear 2 but speed is 6 still but in the range
    changeSpeed(goodCar, 4);

    assertEquals(4, goodCar.getSpeed());


    assertEquals(everythingIsOkay, goodCar.getStatus());
  }

  // when you increase the speed but have increase the gear
  @Test
  public void testMessageIncreaseTheGear() {
    assertEquals(1, goodCar.getGear());

    changeSpeed(goodCar, 10);


    assertEquals(10, goodCar.getSpeed());


    assertEquals(increaseGear, goodCar.getStatus());
  }


  /// when increasing the car then decreasing the car
  //  is the in range but not decreasing the gear
  @Test
  public void testMessageDecreaseTheGearAtGear1() {

    changeSpeed(goodCar, 10);

    ManualTransmission oldCar = goodCar.increaseGear();

    assertEquals(2, goodCar.getGear());

    changeSpeed(goodCar, -5);

    assertEquals(2, goodCar.getGear());


    assertEquals(decreaseGear, oldCar.getStatus());

  }


  // needing to increase the gear after increasing the speed
  @Test
  public void testMessageIncreaseTheGearAtGear1() {

    assertEquals(1, goodCar.getGear());
    changeSpeed(goodCar, 10);

    assertEquals(10, goodCar.getSpeed());


    assertEquals(increaseGear, goodCar.getStatus());

  }


  // test for increasing the speed before adjust the gear
  @Test
  public void testMessageIncreaseTheSpeedMustIncreaseTheGearFirst() {


    changeSpeed(goodCar, 10);

    assertEquals(1, goodCar.getGear());
    assertEquals(10, goodCar.getSpeed());

    goodCar.increaseSpeed();
    assertEquals(cannotIncreaseSpeedIncreaseGear, goodCar.getStatus());

  }


  //trying to decrease the speed but needs to adjust gear first
  @Test
  public void testMessageCannotDecreaseTheSpeedMustDecreaseTheGearFirst() {
    changeSpeed(goodCar, 5);
    assertEquals(1, goodCar.getGear());

    goodCar.increaseGear();
    assertEquals(2, goodCar.getGear());

    changeSpeed(goodCar, -1);

    assertEquals(5, goodCar.getSpeed());

    assertEquals(cannotDecreaseSpeedDecreaseGear, goodCar.getStatus());
  }

  // trying to increase the gear before adjusting the speed
  @Test
  public void testMessageCannotIncreaseTheGearMustIncreaseTheSpeedFirst() {

    changeSpeed(goodCar, 4);

    assertEquals(1, goodCar.getGear());
    assertEquals(4, goodCar.getSpeed());
    goodCar.increaseGear();
    assertEquals(1, goodCar.getGear());

    assertEquals(cannotIncreaseGearIncreaseSpeed, goodCar.getStatus());
  }

  // tring to decrease the gear before decreasing the speed
  @Test
  public void testMessageCannotDecreaseTheGearMustDecreaseTheSpeedFirst() {
    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    assertEquals(2, goodCar.getGear());
    changeSpeed(goodCar, 4);

    assertEquals(14, goodCar.getSpeed());
    goodCar.decreaseGear();

    assertEquals(cannotDecreaseGearDecreaseSpeed, goodCar.getStatus());
  }

  // reaching the maximum speed
  @Test
  public void testMessageCannotIncreaseSpeedReachedMaximum() {

    changeSpeed(goodCar, 10);

    goodCar.increaseGear();
    changeSpeed(goodCar, 10);
    goodCar.increaseGear();
    changeSpeed(goodCar, 10);
    goodCar.increaseGear();
    changeSpeed(goodCar, 10);
    goodCar.increaseGear();
    changeSpeed(goodCar, 10);
    changeSpeed(goodCar, 10);


    assertEquals(50, goodCar.getSpeed());
    assertEquals(5, goodCar.getGear());
    assertEquals(cannotIncreaseSpeedMax, goodCar.getStatus());
  }

  //minimum speed
  @Test
  public void testMessageCannotSpeedDecreaseReachedMinimum() {
    changeSpeed(goodCar, -1);

    assertEquals(cannotDecreaseSpeedMin, goodCar.getStatus());

  }

  // gear max
  @Test
  public void testMessageCannotIncreaseGearReachedMaximum() {
    // Did not increase the speed
    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    changeSpeed(goodCar, 10);
    goodCar.increaseGear();

    assertEquals(5, goodCar.getGear());

    assertEquals(cannotIncreaseGearMax, goodCar.getStatus());

  }

  // gear min
  @Test
  public void testMessageCannotDecreaseGearReachedMinimum() {

    assertEquals(1, goodCar.getGear());

    goodCar.decreaseGear();


    assertEquals(cannotDecreaseGearMin, goodCar.getStatus());
  }


  // minimum speed when reaching a negative
  @Test
  public void testMinimumSpeed() {
    // Set the initial speed to the minimum
    changeSpeed(goodCar, -5);
    assertEquals(0, goodCar.getSpeed());

    // Attempt to decrease speed further
    goodCar.decreaseSpeed();
    assertEquals(cannotDecreaseSpeedMin, goodCar.getStatus());
  }


  //tring to decrease gear at lowest gear
  @Test
  public void testLowestGear() {
    // Set the initial gear to the lowest
    changeSpeed(goodCar, 4);
    assertEquals(4, goodCar.getSpeed());
    assertEquals(1, goodCar.getGear());


    goodCar.decreaseGear();
    assertEquals(cannotDecreaseGearMin, goodCar.getStatus());
  }










}
