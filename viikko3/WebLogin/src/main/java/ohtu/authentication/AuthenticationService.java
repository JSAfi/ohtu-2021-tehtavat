package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }

        if (password.length()<8) {
            status.addError("password should have at least 8 characters");
        }

        if (password.equals(passwordConfirmation) == false) {
            status.addError("password and password confirmation do not match");
        }

        // does pw contain only characters ?
        boolean foundDigit = false;

        for(char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                foundDigit = true;
            }
        }

        if(foundDigit == false) {
            status.addError("password does not contain at least one digit");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }
}
