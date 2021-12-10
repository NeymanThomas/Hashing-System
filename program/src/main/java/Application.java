import java.util.*;

public class Application {

    // static scanner to get input from the user
    public final static Scanner scanner = new Scanner(System.in);

    // Entry point for the application
    public static void main(String[] args) {

        while (true) {
            System.out.println("Type: \n0 -> Store Plain Text \n1 -> Store Hash \n2 -> Read from Plain Text JSON file \n3 -> Read from Hashed JSON File \n4 -> exit");
            int response = Integer.parseInt(scanner.nextLine());

            Application app = new Application();

            if (response == 0)
                app.plainText();
            if (response == 1)
                app.hashed();
            if (response == 2)
                app.readJSONPlainText();
            if (response == 3) {
                app.readJSONHashed();
            }
            if (response == 4)
                break;
        }
    }

    /**
     * If the user decides to store their password in plain text, this function
     * collects their username and password and stores the plain text values
     * in the PlainText.json file
     */
    private void plainText() {
        System.out.println("Please provide a username");
        String username = scanner.nextLine();
        System.out.println("Please provide a password");
        String password = scanner.nextLine();

        IOUtility.writePlainText(username, password);
    }

    /**
     * If the user decides to hash their password, this function collects
     * their username and password then hashes said password, finally storing
     * the hash with the username in the Hashes.json file
     */
    private void hashed() {
        System.out.println("Please provide a username");
        String username = scanner.nextLine();
        System.out.println("Please provide a password");
        String password = scanner.nextLine();

        password = HashSystem.SHA256(password);

        IOUtility.writeHashCode(username, password);
    }

    /**
     * If the user decides they want to retrieve their password from the plain text
     * file, they provide a key (which is their username) and it calls the IOUtility
     * class to search for the password
     */
    private void readJSONPlainText() {
        System.out.println("Please provide a key");
        String key = scanner.nextLine();

        IOUtility.readPlainTextJSON(key);
    }

    /**
     * If the user decides they want to retrieve their password from the Hash file,
     * they provide a key (their username) and it calls the IOUtility class to
     * search for the hashed password
     */
    private void readJSONHashed() {
        System.out.println("Please provide a key");
        String key = scanner.nextLine();

        IOUtility.readHashedJSON(key);
    }
}
