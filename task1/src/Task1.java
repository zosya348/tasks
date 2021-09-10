import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        int n = 5;
        int m = 4;
        int count = 0;
        boolean isOk = true;
        List<Integer> first = new ArrayList<>();
        first.add(arr[0]);

        while (isOk) {
            for (int i : arr) {
                if (i > n) {
                    continue;
                }

                count++;

                if (count == m) {
                    count = 0;

                    if (i == first.get(0)) {
                        isOk = false;
                        break;
                    }
                }

                if (count == 0) {
                    first.add(i);
                    count++;
                }
            }
        }

        for (Integer i : first) {
            System.out.println(i);
        }
    }
}
