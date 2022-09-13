import java.util.ArrayList;
import java.util.List;

public class Authenticate {


    public boolean UserValidation(String a, String b){
        List<User> userList = new ArrayList<>();
        userList.add(new User("anna", "losen"));
        userList.add( new User("berit", "123456"));
        userList.add (new User("kalle", "password"));

        for (User user : userList) {
            if (a.equals(user.username) && b.equals(user.password)) {
            return true;
            }
        }
        return false;
    }
}
