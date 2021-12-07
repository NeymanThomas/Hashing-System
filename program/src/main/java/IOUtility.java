import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.FileReader;

/**
 * IOUtility provides static functions for file reading and writing purposes. JSON objects
 * can be written to the PlainText.json and Hashes.json files, the former being used to
 * store the example bad practice plain text passwords, and the latter being used to store
 * the hashed passwords to the file.
 */
public final class IOUtility {

    @SuppressWarnings("unchecked")
    public static void writeHashCode(String username, String hash) {
        JSONObject login = new JSONObject();
        login.put(username, hash);

        JSONParser jsonParser = new JSONParser();
        JSONArray hashList = new JSONArray();

        try {
            FileReader reader = new FileReader("JsonFiles/Hashes.json");
            Object obj = jsonParser.parse(reader);
            hashList = (JSONArray) obj;
            hashList.add(login);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("JsonFiles/Hashes.json");
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

    @SuppressWarnings("unchecked")
    public static void readJSON(String key) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("JsonFiles/PlainText.json");
            Object obj = jsonParser.parse(reader);
            JSONArray passwordList = (JSONArray) obj;

            passwordList.forEach(info -> parsePasswords((JSONObject) info, key));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void parsePasswords(JSONObject info, String key) {
        String password = (String) info.get(key);
        System.out.println(password);
    }
}
