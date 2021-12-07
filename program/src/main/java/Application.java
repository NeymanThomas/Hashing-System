import java.util.*;

public class Application {

    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type '0' to store info as Plain Text, type '1' to store info hashed");
        int response = Integer.parseInt(scanner.nextLine());

        Application app = new Application();

        if (response == 0)
            app.plainText(scanner);
        if (response == 1)
            app.hashed(scanner);

         */
        // For testing currently
        HashSystem.hashSHA256("Hello");
    }

    private void plainText(Scanner scanner) {
        System.out.println("Please provide a username");
        String username = scanner.nextLine();
        System.out.println("Please provide a password");
        String password = scanner.nextLine();

        IOUtility.writePlainText(username, password);
    }

    private void hashed(Scanner scanner) {
        System.out.println("Please provide a username");
        String username = scanner.nextLine();
        System.out.println("Please provide a password");
        String password = scanner.nextLine();

        password = HashSystem.hashSHA256(password);

        //IOUtility.writeHashCode(username, password);
    }
}
