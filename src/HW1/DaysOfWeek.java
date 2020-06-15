package HW1;

public enum DaysOfWeek {
    MONDAY(8), TUESDAY(8), WEDNESDAY(8), THURSDAY(8), FRIDAY(8), SATURDAY(0), SUNDAY(0);

    private int workingHours;

    DaysOfWeek(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getWorkingHours() {
        return workingHours;
    }
}
