import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {

    public static void main(String[] args) {
        List<String> numbersForArray;

        try {
            numbersForArray = readFiles(args[0]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + args[0]);
            return;
        }

        int[] arr = new int[numbersForArray.size()];

        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(numbersForArray.get(i));
        }

        int sum = 0;
        int[] min = new int[arr.length];
        ArrayList<Integer> steps = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    sum += arr[i] - arr[j];
                }
                if (arr[i] < arr[j]) {
                    sum += arr[j] - arr[i];
                }
            }

            steps.add(sum);
            min[i] = sum;
            sum = 0;
        }

        System.out.println(Collections.min(steps));
    }

    private static List<String> readFiles(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), UTF_8);
    }
}
