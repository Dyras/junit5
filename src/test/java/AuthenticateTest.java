import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;


public class AuthenticateTest {
    private final Authenticate authenticate = new Authenticate();

    @ParameterizedTest
    @CsvSource(value = {"anna, losen, true" , "berit, 123456, true", "kalle, passwor, true" } )
    public void authenticateTest(String a, String b, boolean expected){
        //Given

        // String a och String b är given

        //When
        String returnedToken = authenticate.TokenCheck(a, b);
        boolean result = authenticate.UserValidation(a, b, returnedToken);
        System.out.println(result);

        //Then
        assertEquals(expected, result);

    }
}
