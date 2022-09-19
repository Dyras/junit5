import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.*;

public class Authenticate {
    final List<User> USER_LIST = Arrays.asList(new User("anna", "losen"), new User("berit", "123456"), new User("kalle", "password"));
    final private String RANDOM_TEXT = (UUID.randomUUID().toString());
    final private Key KEY = Keys.hmacShaKeyFor(RANDOM_TEXT.getBytes());

    public boolean UserValidation(String username, String password, String token) {
        String validOrNot = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(password, String.class);
        if (Objects.equals(validOrNot, username)) {
            System.out.println("Din token är: " + token);
            System.out.println("Token validerad. Din token var Giltig!");
            return true;
        } else
            System.out.println("Token kunde INTE valideras. Fel token!");
        return false;
    }

    public String TokenCreator(String username, String password) {
        String role = "placeholder";
        if (Objects.equals(username, "anna")) {
            role = "ADMIN";
        } else if (Objects.equals(username, "berit")) {
            role = "TEACHER";
        } else {
            role = "STUDENT";
        }

        for (User user : USER_LIST) {
            if (username.equals(user.username) && password.equals(user.password)) {
                String token = Jwts.builder()
                        .setSubject(username)
                        .addClaims(Map.of(user.password, user.username))
                        .claim("ROLE", role)
                        .signWith(KEY)
                        .compact();

                return token;

            }
        }
        // Det här resulterar i en exception då denna token har för få kommatecken för en riktig token
        return "falsetoken";
    }

    public String roleGet(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
        String role = claims.get("ROLE", String.class);
        return role;
    }

    public String[] roleControl(String token, String domain) {
        String role = roleGet(token);
        if (Objects.equals(role, "ADMIN")) {
            if (Objects.equals(domain, "GRADING")) {
                return new String[]{"GRADING", "READ, WRITE"};
            } else{
                return new String[]{"COURSE_FEEDBACK", "READ, WRITE"};
            }
        } else if (Objects.equals(role, "STUDENT")) {
            if (Objects.equals(domain, "GRADING")) {
                return new String[]{"GRADING", "READ"};
            } else{
                return new String[]{"COURSE_FEEDBACK", "READ, WRITE"};
            }

            }
        else if (Objects.equals(role, "TEACHER")){
            if (Objects.equals(domain, "GRADING")){
                return new String[]{"GRADING", "READ, WRITE"};
            } else{
                return new String[]{"COURSE_FEEDBACK", "WRITE"};
            }
        }
    return null;
    }
}
