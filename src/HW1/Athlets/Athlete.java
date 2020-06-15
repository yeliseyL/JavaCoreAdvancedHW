package HW1.Athlets;

public class Athlete implements Activities{
    protected String name;
    protected int runMaxDist;
    protected int jumpMaxHeight;
    protected boolean isOnDistance;

    public Athlete(String name, int runMaxDist, int jumpMaxHeight) {
        this.name = name;
        this.runMaxDist = runMaxDist;
        this.jumpMaxHeight = jumpMaxHeight;
        isOnDistance = true;
    }

    @Override
    public void run(int distance) {
        if (!isOnDistance) {
            System.out.printf("%s is off distance.%n", name);
            return;
        }

        if (distance <= runMaxDist) {
            System.out.printf("%s has run %d meters.%n", name, distance);
        } else {
            System.out.printf("%s can't run this long. Getting off distance. %n", name);
            isOnDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance) {
            System.out.printf("%s is off distance.%n", name);
            return;
        }

        if (height <= jumpMaxHeight) {
            System.out.printf("%s has jumped %d meters up.%n", name, height);
        } else {
            System.out.printf("%s can't jump this high. Getting off distance. %n", name);
            isOnDistance = false;
        }
    }
}
