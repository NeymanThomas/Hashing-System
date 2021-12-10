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

    /**
     * This function takes the already existing Hashes.json file and parses its
     * data into a json array. The username and string hash are then appended
     * into the array and the array is stored back onto the file.
     * @param username The username of the user
     * @param hash the hash of the password they provided
     */
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
            System.out.println("Information successfully stored");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This function takes the already existing PlainText.json file and parses its
     * data into a json array. The username and password are then appended
     * into the array and the array is stored back onto the file.
     * @param username The username of the user
     * @param password the password the user provided
     */
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
            System.out.println("Information successfully stored (Unsafely...)");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * this function is invoked when the user wishes to view their hash in the
     * Hashes.json file. It retrieves the file then loops through the JSON objects
     * trying to match the key with the username provided. If there is a match,
     * the hash is then printed to the screen
     * @param key The username of the user
     */
    @SuppressWarnings("unchecked")
    public static void readHashedJSON(String key) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("JsonFiles/Hashes.json");
            Object obj = jsonParser.parse(reader);
            JSONArray passwordList = (JSONArray) obj;

            passwordList.forEach(info -> parsePasswords((JSONObject) info, key));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this function is invoked when the user wishes to view their password in the
     * PlainText.json file. It retrieves the file then loops through the JSON objects
     * trying to match the key with the username provided. If there is a match,
     * the password is printed to the screen.
     * @param key the username provided
     */
    @SuppressWarnings("unchecked")
    public static void readPlainTextJSON(String key) {
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

    /**
     * Simple function that converts a JSONObject and parses it to a string.
     * If the key matches the JSONObject string, the password is then printed
     * @param info the JSONObject to be converted to a string
     * @param key The username being searched
     */
    private static void parsePasswords(JSONObject info, String key) {
        String password = (String) info.get(key);
        if (password != null) {
            System.out.println(password);
        }
    }
}
