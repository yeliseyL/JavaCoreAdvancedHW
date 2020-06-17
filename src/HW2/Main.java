package HW2;

public class Main {
    public static void main(String[] args) {

        final String[][] arrValid = {
                {"5", "8", "41", "2"},
                {"1", "62", "22", "7"},
                {"15", "3", "9", "12"},
                {"71", "3", "32", "6"},
        };

        final String[][] arrInvalid1 = {
                {"5", "8", "41", "2"},
                {"1", "62", "22"},
                {"15", "3"}
        };

        final String[][] arrInvalid2 = {
                {"5", "8", "41", "2"},
                {"1", "2", "22", "7"},
                {"15", "6rew2", "9", "12"},
                {"71", "3", "32", "6"},
        };

        checkArr(arrValid);
        checkArr(arrInvalid1);
        checkArr(arrInvalid2);
    }

    //обработка исключений вынесена в отдельный метод чтобы не дублировать код в main
    public static void checkArr(String[][] arr) {
        try {
            System.out.println(getIntSum(arr));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int getIntSum(String[][] arr) {
        int sum = 0;

        if (arr.length != 4 || arr[0].length != 4 || arr[1].length != 4
                || arr[2].length != 4 || arr[3].length != 4) {
            throw new MyArraySizeException("Wrong array size");
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Wrong data format in element: [%d][%d]", i, j), i, j);
                }
            }
        }
        return sum;
    }
}
