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
            final String CLAIMS_ROLE = authenticate.roleGet(token);
            System.out.println("Grattis! Inloggningen lyckades!");
            System.out.println("Du har användarrättigheten: " + CLAIMS_ROLE);
            System.out.println("Vilken domän vill du veta dina behörigheter på?");
            System.out.println("Alternativ: Grading eller Course_feedback");
            String domain;
            try {
                domain = new Scanner(System.in).nextLine().toUpperCase();
                if (!domain.equals("GRADING") && !domain.equals("COURSE_FEEDBACK")){
                    System.out.println("Felaktig input! Väljer Grading som standardsvar");
                    domain = "GRADING";
                }
            } catch(Exception e){
                System.out.println("Felaktig input! Väljer Grading som standardsvar");
                domain = "GRADING";
            }
            String[] list;
            list = authenticate.roleControl(token, domain);
            System.out.println("Dina behörigheter inom: " + list[0]);
            System.out.println(list[1]);

        } else
            System.out.println("Beklagar! Fel användarnamn eller lösenord!");
    }
}
