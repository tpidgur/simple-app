package ua.pidhurska.lesson.maven;

import java.time.LocalDate;

/**
 * class that contains the current indices of steps, water, food for the person and their min and max boundaries
 * for every day
 */
public class DailyActivity {
    /**
     * max daily indices of steps, water, food
     */

    private int max_water = 3500;
    private int max_food = 2600;
    private int max_steps = 12000;


    /**
     * min daily indices of steps, water, food
     */

    private int min_water = 2000;
    private int min_food = 1300;
    private int min_steps = 6000;
    /**
     * current indices of steps, water, food
     */
    private int steps;
    private int water;
    private int food;
    /**
     * current statuses of consumed water,food, steps
     */
    private Status waterStatus;
    private Status stepsStatus;
    private Status foodStatus;
    /**
     * current date when the indices are recorded
     */
    private LocalDate date = LocalDate.now();

    /**
     * increases the amount of made steps
     * and updates the status
     *
     * @param step_number int
     */
    public void walk(int step_number) {
        steps += step_number;
        updateStatus();
    }


    /**
     * increases the amount of consumed  water in milliliters
     * and updates the status
     *
     * @param water_milliliters int
     */
    public void drink(int water_milliliters) {
        water += water_milliliters;
        updateStatus();
    }

    /**
     * increases the amount of consumed  food in kCalories
     * and updates the status
     *
     * @param kCalories int
     */
    public void eat(int kCalories) {
        food += kCalories;
        updateStatus();
    }


    /**
     * calculates the steps that are left to match the low boundary of the Normal status
     *
     * @return int steps
     */
    public int stepsLeft() {
        return min_steps - steps;
    }

    /**
     * calculates the milliliters that are left to match the low boundary of the Normal status
     *
     * @return int milliliters
     */
    public int waterLeft() {
        return min_water - water;
    }

    /**
     * calculates the kCalories that are left to match the low boundary of the Normal status
     *
     * @return int kCalories
     */
    public int foodLeft() {
        return min_food - food;
    }

    /**
     * updates the statuses of consumed water, food, made steps based on the current indices
     */
    public void updateStatus() {
        if (water < max_water && water > min_water) {
            waterStatus = Status.NORM;
        } else if (water < min_water) {
            waterStatus = Status.NOT_ENOUGH;
        } else {//(water>max_water)
            waterStatus = Status.EXCESSIVE;
        }

        if (food < max_food && food > min_food) {
            foodStatus = Status.NORM;
        } else if (food < min_food) {
            foodStatus = Status.NOT_ENOUGH;
        } else {//(food>max_food)
            foodStatus = Status.EXCESSIVE;
        }

        if (steps < max_steps && food > min_steps) {
            stepsStatus = Status.NORM;
        } else if (steps < min_steps) {
            stepsStatus = Status.NOT_ENOUGH;
        } else {//(steps>max_steps)
            stepsStatus = Status.EXCESSIVE;
        }
    }

    /**
     * all the bad statuses among three criteria (consumed water, food, made steps) are concated in a String
     * @return String
     */
    public String badStatuses() {
        StringBuilder badStatus = new StringBuilder();
        if (waterStatus == Status.NOT_ENOUGH) {
            badStatus.append("WaterStatus " + Status.NOT_ENOUGH);
        } else if (waterStatus == Status.EXCESSIVE) {
            badStatus.append("WaterStatus " + Status.EXCESSIVE);
        }
        if (foodStatus == Status.NOT_ENOUGH) {
            badStatus.append("FoodStatus " + Status.NOT_ENOUGH);
        } else if (foodStatus == Status.EXCESSIVE) {
            badStatus.append("FoodStatus " + Status.EXCESSIVE);
        }
        if (stepsStatus == Status.NOT_ENOUGH) {
            badStatus.append("StepsStatus " + Status.NOT_ENOUGH);
        } else if (stepsStatus == Status.EXCESSIVE) {
            badStatus.append("StepsStatus " + Status.EXCESSIVE);
        }
        return badStatus.toString();
    }

    /**
     * summarizes daily consumed amount of water, food, made steps
     * @return String
     */
    public String getDailyStatistics() {
        StringBuilder dailyStatistics = new StringBuilder();
        dailyStatistics.append("Daily consumed water: " + water + "/n");
        dailyStatistics.append("Daily consumed food: " + food + "/n");
        dailyStatistics.append("Daily consumed steps: " + steps + "/n");
        return dailyStatistics.toString();
    }
    /**
     * summarizes how much is left to match the daily statistics on  water, food, made steps in %
     * @return String
     */
    public String getRemainderDailyStatistics() {
        StringBuilder dailyStatistics = new StringBuilder();
        if (foodStatus == Status.NOT_ENOUGH){
            dailyStatistics.append("Remainder food (%): " + (100*foodLeft()/min_food));
        }
        if (waterStatus == Status.NOT_ENOUGH){
            dailyStatistics.append(". Remainder water (%): " + (100*waterLeft()/min_water));
        }
        if (stepsStatus == Status.NOT_ENOUGH){
            dailyStatistics.append(". Remainder steps (%): " + (100*stepsLeft()/min_steps));
        }
        return dailyStatistics.toString();
    }

    public Status getWaterStatus() {
        return waterStatus;
    }

    public Status getStepsStatus() {
        return stepsStatus;
    }

    public Status getFoodStatus() {
        return foodStatus;
    }

    public int getSteps() {
        return steps;
    }

    public int getWater() {
        return water;
    }

    public int getFood() {
        return food;
    }


    public LocalDate getDate() {
        return date;
    }

    public int getMin_water() {
        return min_water;
    }

    public int getMin_food() {
        return min_food;
    }

    public int getMin_steps() {
        return min_steps;
    }
}
