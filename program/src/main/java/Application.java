import java.util.*;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type: \n0 -> Store Plain Text \n1 -> Store Hash \n2 -> Read from JSON file");
        int response = Integer.parseInt(scanner.nextLine());

        Application app = new Application();

        if (response == 0)
            app.plainText(scanner);
        if (response == 1)
            app.hashed(scanner);
        if (response == 2)
            app.readJSONPlainText(scanner);
    }

    /**
     * If the user decides to store their password in plain text, this function
     * collects their username and password and stores the plain text values
     * in the PlainText.json file
     * @param scanner The scanner created to take user input
     */
    private void plainText(Scanner scanner) {
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
     * @param scanner The scanner created to take user input
     */
    private void hashed(Scanner scanner) {
        System.out.println("Please provide a username");
        String username = scanner.nextLine();
        System.out.println("Please provide a password");
        String password = scanner.nextLine();

        password = HashSystem.SHA256(password);

        IOUtility.writeHashCode(username, password);
    }

    private void readJSONPlainText(Scanner scanner) {
        System.out.println("Please provide a key");
        String key = scanner.nextLine();

        IOUtility.readJSON(key);
    }
}
