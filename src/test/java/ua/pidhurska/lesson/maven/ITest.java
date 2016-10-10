package ua.pidhurska.lesson.maven;

import org.junit.Before;
import org.junit.Test;
import ua.pidhurska.lesson.maven.DailyActivity;

import static org.junit.Assert.assertEquals;

/**
 * Tests the DailyActivity class
 */
public class ITest {
    /**
     * tests if the total consumed water is correctly calculated
     * (when summing  the counterparts)
     */
    @Test
    public void drinkTest() {
        DailyActivity activity = new DailyActivity();
        activity.drink(1000);
        activity.drink(4000);
        assertEquals(5000, activity.getWater());
    }

    /**
     * checks the correctness of the status depending on the consumed water
     * (the  EXCESSIVE status when the norm range has been overcome)
     */
    @Test
    public void checkMaxWater() {
        DailyActivity activity = new DailyActivity();
        activity.drink(3600);
        assertEquals(Status.EXCESSIVE, activity.getWaterStatus());
    }

    /**
     * checks that the lower boundary of the Norm status of the daily consumed water isn't overcome
     */
    @Test
    public void checkMinWater() {
        DailyActivity activity = new DailyActivity();
        activity.drink(1600);
        assertEquals(Status.NOT_ENOUGH, activity.getWaterStatus());
    }

    /**
     * tests the amount of daily steps left to match the low boundary of the norm range of steps
     */
    @Test
    public void stepsLeftTest() {
        DailyActivity activity = new DailyActivity();
        activity.walk(1000);
        activity.walk(1000);
        assertEquals(4000, activity.stepsLeft());
    }


}
