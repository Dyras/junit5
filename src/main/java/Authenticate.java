import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.*;

public class Authenticate {
    final List<User> userList = Arrays.asList(new User("anna", "losen"), new User("berit", "123456"), new User("kalle", "password"));
    final private String randomText = (UUID.randomUUID().toString());
    final private Key key = Keys.hmacShaKeyFor(randomText.getBytes());

    public boolean UserValidation(String username, String password, String token ) {
        String validOrNot = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(password, String.class);
        if (Objects.equals(validOrNot, username))
        {
            System.out.println("Din token är: " + token);
            System.out.println("Token validerad. Din token var Giltig!");
            return true;
        }
        else
            System.out.println("Token kunde INTE valideras. Fel token!");
        return false;
    }

    public String TokenCreator(String username, String password) {

            for (User user : userList) {
                if (username.equals(user.username) && password.equals(user.password)) {
                    String token = Jwts.builder()
                            .setSubject(username)
                            .addClaims(Map.of(user.password, user.username))
                            .signWith(key)
                            .compact();

                    return token;

                }
            }
            // Det här resulterar i en exception då denna token har för få kommatecken för en riktig token
            return "falsetoken";
        }
        }
