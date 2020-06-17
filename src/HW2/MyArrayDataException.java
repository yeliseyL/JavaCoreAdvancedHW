package HW2;

public class MyArrayDataException extends NumberFormatException {
    private int i;
    private int j;

    public MyArrayDataException() {
    }

    public MyArrayDataException(String s, int i, int j) {
        super(s);
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
