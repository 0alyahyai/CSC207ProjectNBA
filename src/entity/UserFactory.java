package entity;

import java.time.LocalDateTime;

//This is the UserFactory interface. It declares a method called create, which takes a String, a String, and a LocalDateTime
//as parameters, and returns a User. It depends on the User class (entity).
public interface UserFactory {
    /** Requires: password is valid. */
    User create(int userID, String userName, String userPassword);
}
