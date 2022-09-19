import org.junit.jupiter.api.Test;
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
        String returnedToken = authenticate.TokenCreator(a, b);
        boolean result = authenticate.UserValidation(a, b, returnedToken);

        //Then
        assertEquals(expected, result);

    }

    @Test
    public void tokenTest(){
        //Given
        String a = "anna";
        String b = "losen";
        String expected = "ADMIN";

        //When
        String returnedToken = authenticate.TokenCreator(a, b);
        String result = authenticate.roleGet(returnedToken);
        //Then

        assertEquals(expected, result);
    }
}
