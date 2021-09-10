import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        List<String> centerAndRadius;
        List<String> coordinates;

        try {
            centerAndRadius = readFile(args[0]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + args[0]);
            return;
        }

        try {
            coordinates = readFile(args[1]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + args[1]);
            return;
        }

        String[] temp = centerAndRadius.get(0).split(" ", 2);
        float centerX = Float.parseFloat(temp[0]);
        float centerY = Float.parseFloat(temp[1]);
        float radius = Float.parseFloat(centerAndRadius.get(1));

        for (String s : coordinates) {
            temp = s.split(" ", 2);
            float pointX = Float.parseFloat(temp[0]);
            float pointY = Float.parseFloat(temp[1]);
            double distance = distanceBetweenTwoPoints(centerX, centerY, pointX, pointY);

            if (Math.abs(distance - radius) < 0.00001) {
                System.out.println("0");
            } else if (distance > radius) {
                System.out.println("2");
            } else {
                System.out.println("1");
            }
        }
    }

    public static List<String> readFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), UTF_8);
    }

    public static double distanceBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
