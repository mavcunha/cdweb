package security;

import controllers.*;
import models.User;

public class Authenticator extends Secure.Security {
    static boolean authenticate(String username, String password) {
        return User.connect(username, password) != null;
    }
}
