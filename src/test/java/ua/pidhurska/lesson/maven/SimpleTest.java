package ua.pidhurska.lesson.maven;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.pidhurska.lesson.maven.GlobalActivity;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class SimpleTest {

    /**
     * checks that action "Drink 500 milliliters of water on 2016,05,17" will be added to the map activitySummary
     */
    @Test
    public void addEventTest() {
        GlobalActivity activities = new GlobalActivity();
        LocalDate date = LocalDate.of(2016, 05, 17);
        activities.addEvent(Event.WATER, 500, date);
        assertEquals(500, activities.getDailyActivityOnDate(date).getWater());
    }

    /**
     * checks that by adding different activities on different dates do not coincide between each other
     */
    @Test
    public void addDifferentEventsTest() {
        GlobalActivity activities = new GlobalActivity();
        LocalDate date = LocalDate.of(2016, 05, 17);
        activities.addEvent(Event.WATER, 500, date);
        activities.addEvent(Event.WATER, 100, date);
        activities.addEvent(Event.WATER, 400, date);

        activities.addEvent(Event.STEPS, 500, date);
        activities.addEvent(Event.STEPS, 100, date);
        activities.addEvent(Event.STEPS, 400, date);

        activities.addEvent(Event.FOOD, 500, date);
        activities.addEvent(Event.FOOD, 100, date);
        activities.addEvent(Event.FOOD, 400, date);

        activities.addEvent(Event.WATER, 400, date.minusDays(1));
        assertEquals(1000, activities.getDailyActivityOnDate(date).getWater());
    }

    /**
     * tests that the week report with average indices on water, food, steps are correctly performed
     */
    @Test
    public void addReportTest() {
        GlobalActivity activities = new GlobalActivity();
        LocalDate date = LocalDate.of(2016, 05, 10);
        activities.addEvent(Event.WATER, 500, date);
        activities.addEvent(Event.WATER, 100, LocalDate.of(2016, 05, 11));
        activities.addEvent(Event.WATER, 400, LocalDate.of(2016, 05, 12));

        activities.addEvent(Event.STEPS, 500, LocalDate.of(2016, 05, 13));
        activities.addEvent(Event.STEPS, 100, LocalDate.of(2016, 05, 14));
        activities.addEvent(Event.STEPS, 400, LocalDate.of(2016, 05, 15));

        activities.addEvent(Event.FOOD, 500, LocalDate.of(2016, 05, 16));

        String report = activities.addWeekReport(date);
        assertEquals("Mean values: water=142,food=71,steps=142", report);
    }

}
