package HW1.Obstacles;

import HW1.Athlets.Athlete;

public class Track implements Obstacle {
    private int distance;

    public Track(int distance) {
        this.distance = distance;
    }

    @Override
    public void overcome(Athlete athlete) {
        athlete.run(distance);
    }
}
