package HW1;

import HW1.Athlets.Athlete;
import HW1.Athlets.Cat;
import HW1.Athlets.Human;
import HW1.Athlets.Robot;
import HW1.Obstacles.Obstacle;
import HW1.Obstacles.Track;
import HW1.Obstacles.Wall;

public class Main {
    public static void main(String[] args) {
        Athlete[] athlets = {
                new Human("Vasya"),
                new Robot("Fyodor"),
                new Cat("Murzik")
        };

        Obstacle[] obstacles = {
                new Track(500),
                new Wall(1)
        };

        for (Athlete athlet : athlets) {
            for (Obstacle obstacle : obstacles) {
                obstacle.overcome(athlet);
            }
        }

        workingHours(DaysOfWeek.WEDNESDAY);
    }

    public static void workingHours(DaysOfWeek day) {
        if (day.getWorkingHours() == 0) {
            System.out.println("Today is weekend.");
        } else {
            System.out.println("Working hours left: " + (5 - day.ordinal()) * day.getWorkingHours());
        }
    }
}
