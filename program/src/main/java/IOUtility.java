import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.FileReader;

public final class IOUtility {

    @SuppressWarnings("unchecked")
    public static void writeHashCode(String username, String hash) {
        JSONObject login = new JSONObject();
        login.put(username, hash);

        JSONParser jsonParser = new JSONParser();
        JSONArray hashList = new JSONArray();

        try {
            FileReader reader = new FileReader("JsonFiles/PlainText.json");
            Object obj = jsonParser.parse(reader);
            hashList = (JSONArray) obj;
            hashList.add(login);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("JsonFiles/PlainText.json");
            file.write(hashList.toJSONString());
            file.flush();
            file.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void writePlainText(String username, String password) {
        JSONObject login = new JSONObject();
        login.put(username, password);

        JSONParser jsonParser = new JSONParser();
        JSONArray passwordList = new JSONArray();

        try {
            FileReader reader = new FileReader("JsonFiles/PlainText.json");
            Object obj = jsonParser.parse(reader);
            passwordList = (JSONArray) obj;
            passwordList.add(login);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("JsonFiles/PlainText.json");
            file.write(passwordList.toJSONString());
            file.flush();
            file.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
