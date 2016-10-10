package ua.pidhurska.lesson.maven;

import ua.pidhurska.lesson.maven.DailyActivity;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Contains a map of days (key) and  daily activities(value)
 */
public class GlobalActivity {
    private Map<LocalDate, DailyActivity> activitySummary = new TreeMap<>();


    public Map<LocalDate, DailyActivity> getActivitySummary() {
        return activitySummary;
    }

    /**
     * according to the event (Water, Food or Steps) the specific action is taken:
     * drink,walk or step. If the DailyRecord on specific date is absent the new record has been added
     *
     * @param type  Event
     * @param value int (amount of steps, water or food)
     * @param date
     */
    public void addEvent(Event type, int value, LocalDate date) {
        DailyActivity activity = null;
        if (activitySummary.get(date) == null) {
            activitySummary.put(date, activity = new DailyActivity());
        } else {
            activity = activitySummary.get(date);
        }
        if (type == Event.WATER) {
            activity.drink(value);
        } else if (type == Event.FOOD) {
            activity.eat(value);
        } else {
            activity.walk(value);
        }
    }

    /**
     * @param date LocalDate
     * @return DailyActivity value on the key LocalDate object
     */
    public DailyActivity getDailyActivityOnDate(LocalDate date) {
        return activitySummary.get(date);
    }

    /**
     * summarizes the mean values of consumed water, food, made steps during the week starting from some date
     *
     * @param date LocalDate
     * @return String
     */
    public String addWeekReport(LocalDate date) {
        int weekWater = 0;
        int weekFood = 0;
        int weekSteps = 0;
        for (int i = 0; i < 7; i++) {
            weekWater += activitySummary.get(date.plusDays(i)).getWater();
            weekFood += activitySummary.get(date.plusDays(i)).getFood();
            weekSteps += activitySummary.get(date.plusDays(i)).getSteps();
        }
        return "Mean values: water=" + weekWater / 7 + ",food=" + weekFood / 7
                + ",steps=" + weekSteps / 7;
    }
}
