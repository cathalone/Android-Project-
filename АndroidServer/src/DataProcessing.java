import java.util.Objects;

public class DataProcessing {
    public static void parseData(String data, int[] parsedData) {
        if (!Objects.equals(data, "")) {
            String[] splitData = data.split(",");
            for (int i = 0; i < 3; i++) {
                parsedData[i] = Integer.parseInt(splitData[i]);
            }
        }
    }
    public static void printArray(int[] array) {
        System.out.println("x: " + array[0] + ", y: " + array[1] + ", z: " + array[2]);
    }
}
