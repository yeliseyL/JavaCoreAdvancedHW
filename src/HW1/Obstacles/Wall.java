package HW1.Obstacles;

import HW1.Athlets.Athlete;

public class Wall implements Obstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void overcome(Athlete athlete) {
        athlete.jump(height);
    }
}
