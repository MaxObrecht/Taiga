import java.util.HashMap;

/**

  * This class stores the login information for users
  * and allows the adding of users
  * 
  * @author Tyler Page
  * @version 1.0
  *
**/

public class LoginStorage {
    // Obviously not secure or practical, for testing purposes
    private HashMap<String, String> logins;

    public LoginStorage() {
        logins = new HashMap<>();
    }

    public void addUser(String username, String password) {
        logins.put(username, password);
    }

    public boolean validateLogin(String username, String password) {
        return logins.containsKey(username) && logins.get(username).equals(password);
    }
}
