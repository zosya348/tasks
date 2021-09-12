import static java.nio.charset.StandardCharsets.UTF_8;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) {
        Gson g = new Gson();
        String testsText;
        String valuesText;

        try {
            testsText = readFile(args[0]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла тестов");
            return;
        }

        try {
            valuesText = readFile(args[1]);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла values");
            return;
        }

        Tests tests = g.fromJson(testsText, Tests.class);
        Values values = g.fromJson(valuesText, Values.class);
        Map<Integer, String> valuesMap = new HashMap<>();

        for(Value v: values.getValues()){
            valuesMap.put(v.getId(), v.getValue());
        }

        proccessTests(tests.getTests(), valuesMap);
        String result = g.toJson(tests);

        try {
            saveFile("report.json", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)), UTF_8);
    }

    public static void proccessTests(Test[] tests, Map<Integer, String> valuesMap){
        for (Test t : tests) {
            t.setValue(valuesMap.get(t.getId()));
            if(t.getValues() != null){
                proccessTests(t.getValues(), valuesMap);
            }
        }
    }

    public static void saveFile(String filePath, String fileText) throws IOException {
        Files.write(Paths.get(filePath), fileText.getBytes(UTF_8));
    }
}
