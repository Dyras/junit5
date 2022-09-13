import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Authenticate authenticate = new Authenticate();
        String usernameInput = "Placeholder";
        String passwordInput = "Placeholder";

    while(Objects.equals(usernameInput, "Placeholder")) {
        System.out.println("Skriv in ditt användarnamn: ");
        usernameInput = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
    }
    while(Objects.equals(passwordInput, "Placeholder")) {
        System.out.println("Skriv in ditt lösenord: ");
        passwordInput = new Scanner(System.in).nextLine();
    }
    String token = authenticate.TokenCreator(usernameInput, passwordInput);
    boolean result = authenticate.UserValidation(usernameInput, passwordInput, token);
        if (result){
            System.out.println("Grattis! Inloggningen lyckades!");
        } else
            System.out.println("Beklagar! Fel användarnamn eller lösenord!");
    }
}
